package unlp.info.chatbot.operation;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.AddCategoryWitRequest;
import unlp.info.chatbot.client.request.AddItemWitRequest;
import unlp.info.chatbot.client.request.AddPhraseWitRequest;
import unlp.info.chatbot.client.request.PutEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddCategoryOperationRequest;
import unlp.info.chatbot.operation.request.AddPhraseOperationRequest;
import unlp.info.chatbot.transformer.PersistentTransformer;

import javax.annotation.Resource;

@Component
public class AddCategoryOperation extends AbstractAddEntityOperation<AddCategoryOperationRequest, EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddCategoryOperation.class);

  private PersistentTransformer<AddCategoryOperationRequest, EntityPersistent> categoryPersistentTransformer;
  private Client<AddCategoryWitRequest,AddEntityWitResponse> addCategoryWitClient;
  private Client<PutEntityWitRequest, AddEntityWitResponse> putEntityWitClient;
  private Client<AddItemWitRequest, AddEntityWitResponse> addItemWitClient;
  private Client<AddPhraseWitRequest, AddEntityWitResponse> addPhraseWitClient;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }



  @Override
  protected AddEntityWitResponse callWit(AddCategoryOperationRequest request) {

    AddCategoryWitRequest addCategoryWitRequest = new AddCategoryWitRequest();
    addCategoryWitRequest.setEntity(request.getEntity());
    addCategoryWitRequest.setDescription(request.getDescription());

    AddEntityWitResponse addEntityWitResponse = this.addCategoryWitClient.call(addCategoryWitRequest);

    PutEntityWitRequest putEntityWitRequest = new PutEntityWitRequest();
    putEntityWitRequest.setEntityName(addEntityWitResponse.getName());
    putEntityWitRequest.setLookups(Lists.newArrayList("\"free-text\"", "\"keywords\""));
    this.putEntityWitClient.call(putEntityWitRequest);

    AddItemWitRequest addItemWitRequest = new AddItemWitRequest();
    addItemWitRequest.setEntity("categories");
    addItemWitRequest.setItemId(request.getEntity());
    addItemWitRequest.setDescription(request.getDescription());

    this.addItemWitClient.call(addItemWitRequest);

    AddPhraseWitRequest addPhraseWitRequest = new AddPhraseWitRequest();
    addPhraseWitRequest.setEntity("categories");
    addPhraseWitRequest.setItemId(request.getEntity());
    addPhraseWitRequest.setDescription(request.getEntity());

    this.addPhraseWitClient.call(addPhraseWitRequest);

    return addEntityWitResponse;

  }

  @Override
  protected PersistentTransformer<AddCategoryOperationRequest, EntityPersistent> getPersistentTransformer() {
    return this.categoryPersistentTransformer;
  }



  @Resource
  public void setCategoryPersistentTransformer(PersistentTransformer<AddCategoryOperationRequest, EntityPersistent> categoryPersistentTransformer) {
    this.categoryPersistentTransformer = categoryPersistentTransformer;
  }

  @Resource
  public void setAddCategoryWitClient(Client<AddCategoryWitRequest, AddEntityWitResponse> addCategoryWitClient) {
    this.addCategoryWitClient = addCategoryWitClient;
  }

  @Resource
  public void setPutEntityWitClient(Client<PutEntityWitRequest, AddEntityWitResponse> putEntityWitClient) {
    this.putEntityWitClient = putEntityWitClient;
  }

  @Resource
  public void setAddItemWitClient(Client<AddItemWitRequest, AddEntityWitResponse> addItemWitClient) {
    this.addItemWitClient = addItemWitClient;
  }

  @Resource
  public void setAddPhraseWitClient(Client<AddPhraseWitRequest, AddEntityWitResponse> addPhraseWitClient) {
    this.addPhraseWitClient = addPhraseWitClient;
  }
}
