package unlp.info.chatbot.operation;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.request.AddCategoryWitRequest;
import unlp.info.chatbot.client.request.AddItemWitRequest;
import unlp.info.chatbot.client.request.AddPhraseWitRequest;
import unlp.info.chatbot.client.request.PutEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddCategoryOperationRequest;
import unlp.info.chatbot.service.WitService;
import unlp.info.chatbot.transformer.PersistentTransformer;

import javax.annotation.Resource;

@Component
public class AddCategoryOperation extends AbstractAddEntityOperation<AddCategoryOperationRequest, EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddCategoryOperation.class);

  @Resource
  private WitService witService;
  @Resource
  private PersistentTransformer<AddCategoryOperationRequest, EntityPersistent> categoryPersistentTransformer;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }



  @Override
  protected AddEntityWitResponse callWit(AddCategoryOperationRequest request) {

    AddCategoryWitRequest addCategoryWitRequest = new AddCategoryWitRequest();
    addCategoryWitRequest.setEntity(request.getEntity());
    addCategoryWitRequest.setDescription(request.getDescription());

    AddEntityWitResponse addEntityWitResponse = this.witService.addCategory(addCategoryWitRequest);

    PutEntityWitRequest putEntityWitRequest = new PutEntityWitRequest();
    putEntityWitRequest.setEntityName(addEntityWitResponse.getName());
    putEntityWitRequest.setLookups(Lists.newArrayList("\"free-text\"", "\"keywords\""));
    this.witService.updateEntity(putEntityWitRequest);

    AddItemWitRequest addItemWitRequest = new AddItemWitRequest();
    addItemWitRequest.setEntity("categories");
    addItemWitRequest.setItemId(request.getEntity());
    addItemWitRequest.setDescription(request.getDescription());

    this.witService.addItem(addItemWitRequest);

    AddPhraseWitRequest addPhraseWitRequest = new AddPhraseWitRequest();
    addPhraseWitRequest.setEntity("categories");
    addPhraseWitRequest.setItemId(request.getEntity());
    addPhraseWitRequest.setDescription(request.getEntity());

    this.witService.addPhrase(addPhraseWitRequest);

    return addEntityWitResponse;

  }

  @Override
  protected PersistentTransformer<AddCategoryOperationRequest, EntityPersistent> getPersistentTransformer() {
    return this.categoryPersistentTransformer;
  }

}
