package unlp.info.chatbot.operation;

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
import unlp.info.chatbot.model.MessagePersistent;
import unlp.info.chatbot.operation.request.GetMessageRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.service.WitService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class GetMessageOperation implements Operation<GetMessageRequest, MessagePersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetMessageOperation.class);
  private static BigDecimal CONFIDENCE_THRESHOLD = BigDecimal.valueOf(0.8);

  private RepositoryService<MessagePersistent> messageRepositoryService;

  private WitService witService;

  @Override
  public MessagePersistent execute(GetMessageRequest request) {


    GetMessageWitRequest getMessageWitRequest = new GetMessageWitRequest();
    getMessageWitRequest.setMessage(request.getPhrase());

    WitMessageResponse witMessageResponse = this.witService.obtainMessage(getMessageWitRequest);

    Map<String, List<WitMessageMetricResponse>> entities = witMessageResponse.getEntities();
    Set<Map.Entry<String, List<WitMessageMetricResponse>>> metrics = entities.entrySet();

    if (CollectionUtils.isEmpty(metrics)) {
      throw new ItemNotFoundException("[GET MESSAGE OPERATION] The message must have be metrics defined");
    }

    WitMessageMetricResponse metric = metrics.iterator().next().getValue().iterator().next();
    LOGGER.debug("[GET MESSAGE OPERATION] By the moment, only get the first metric");

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

  @Resource
  public void setMessageRepositoryService(RepositoryService<MessagePersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }

  @Resource
  public void setWitService(WitService witService) {
    this.witService = witService;
  }
}
