package unlp.info.chatbot.operation;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import unlp.info.chatbot.client.request.GetMessageWitRequest;
import unlp.info.chatbot.client.response.WitMessageMetricResponse;
import unlp.info.chatbot.client.response.WitMessageResponse;
import unlp.info.chatbot.exception.ItemNotFoundException;
import unlp.info.chatbot.exception.LowConfidenceException;
import unlp.info.chatbot.exception.NullConfidenceException;
import unlp.info.chatbot.model.Entity;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetItemsForCategoryRequest;
import unlp.info.chatbot.operation.request.GetMessageRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.service.WitService;
import unlp.info.chatbot.transformer.ModelTransformer;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static unlp.info.chatbot.model.EntityKind.CATEGORY;
import static unlp.info.chatbot.model.EntityKind.ITEM;

@Component
public class GetMessageOperation implements Operation<GetMessageRequest, Entity> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetMessageOperation.class);
  private static BigDecimal CONFIDENCE_THRESHOLD = BigDecimal.valueOf(0.7);

  private RepositoryService<EntityPersistent> messageRepositoryService;

  private WitService witService;

  private ModelTransformer<EntityPersistent, Entity> entityTransformer;

  @Override
  public Entity execute(GetMessageRequest request) {


    GetMessageWitRequest getMessageWitRequest = new GetMessageWitRequest();
    getMessageWitRequest.setMessage(request.getPhrase());

    WitMessageResponse witMessageResponse = this.witService.obtainMessage(getMessageWitRequest);

    Map<String, List<WitMessageMetricResponse>> entities = witMessageResponse.getEntities();
    Set<Map.Entry<String, List<WitMessageMetricResponse>>> metrics = entities.entrySet();

    if (CollectionUtils.isEmpty(metrics)) {
      throw new ItemNotFoundException("Sorry! but I don't understand");
    }

    Map<String, EntityPersistent> selector = Maps.newHashMap();
    for (WitMessageMetricResponse metric : metrics.iterator().next().getValue()) {
      EntityPersistent entityPersistent = this.getEntityPersistent(metric);
      selector.put(entityPersistent.getKind(), entityPersistent);
    }

    EntityPersistent entityPersistent = this.getEntityPersistentByPriority(metrics, selector);

    return this.entityTransformer.transform(entityPersistent);

  }

  private EntityPersistent getEntityPersistent(WitMessageMetricResponse metric) {

    BigDecimal confidence = metric.getConfidence();

    if (confidence == null) {
      LOGGER.error("[GET MESSAGE OPERATION] Confidence cannot be null");
      throw new NullConfidenceException("Confidence cannot be null");
    }

    if (CONFIDENCE_THRESHOLD.compareTo(confidence) > 0) {
      LOGGER.error("[GET MESSAGE OPERATION] Confidence must be upper than 80%. Confidence: {}", confidence);
      throw new LowConfidenceException("Confidence must be upper than 80%");
    }

    return this.messageRepositoryService.getById(metric.getValue());
  }

  private EntityPersistent getEntityPersistentByPriority(Set<Map.Entry<String, List<WitMessageMetricResponse>>> metrics, Map<String, EntityPersistent> selector) {
    if (CollectionUtils.isEmpty(selector)) {
      throw new ItemNotFoundException("Wit data is not present in DB. Metric: " + metrics.iterator().next().getValue());
    }

    // Select by priority

    if (selector.containsKey(ITEM)) {
      return selector.get(ITEM);
    }

    if (selector.containsKey(CATEGORY)) {
      return selector.get(CATEGORY);
    }

    throw new ItemNotFoundException("The message must have be metrics defined. Metric: " + metrics.iterator().next().getValue());
  }
  

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }

  @Resource
  public void setWitService(WitService witService) {
    this.witService = witService;
  }

  @Resource
  public void setEntityTransformer(ModelTransformer<EntityPersistent, Entity> entityTransformer) {
    this.entityTransformer = entityTransformer;
  }
}
