package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.AddCategoryWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddCategoryRequest;
import unlp.info.chatbot.transformer.PersistentTransformer;

import javax.annotation.Resource;

@Component
public class AddCategoryOperation extends AbstractAddEntityOperation<AddCategoryRequest, EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddCategoryOperation.class);

  private PersistentTransformer<AddCategoryRequest, EntityPersistent> categoryPersistentTransformer;
  private Client<AddCategoryWitRequest,AddEntityWitResponse> addCategoryWitClient;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }



  @Override
  protected AddEntityWitResponse callWit(AddCategoryRequest request) {
    AddCategoryWitRequest addCategoryWitRequest = new AddCategoryWitRequest();
    addCategoryWitRequest.setEntity(request.getEntity());
    addCategoryWitRequest.setDescription(request.getDescription());

    return this.addCategoryWitClient.call(addCategoryWitRequest);
  }

  @Override
  protected PersistentTransformer<AddCategoryRequest, EntityPersistent> getPersistentTransformer() {
    return this.categoryPersistentTransformer;
  }



  @Resource
  public void setCategoryPersistentTransformer(PersistentTransformer<AddCategoryRequest, EntityPersistent> categoryPersistentTransformer) {
    this.categoryPersistentTransformer = categoryPersistentTransformer;
  }

  @Resource
  public void setAddCategoryWitClient(Client<AddCategoryWitRequest, AddEntityWitResponse> addCategoryWitClient) {
    this.addCategoryWitClient = addCategoryWitClient;
  }
}
