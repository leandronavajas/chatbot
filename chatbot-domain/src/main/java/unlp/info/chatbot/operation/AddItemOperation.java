package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.AddItemWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddItemRequest;
import unlp.info.chatbot.transformer.PersistentTransformer;

import javax.annotation.Resource;

@Component
public class AddItemOperation extends AbstractAddEntityOperation<AddItemRequest, EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddItemOperation.class);
  private Client<AddItemWitRequest, AddEntityWitResponse> addItemWitClient;
  private PersistentTransformer<AddItemRequest, EntityPersistent> itemPersistentTransformer;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }



  @Override
  protected AddEntityWitResponse callWit(AddItemRequest request) {
    AddItemWitRequest witRequest = new AddItemWitRequest();
    witRequest.setEntity(request.getEntity());
    witRequest.setItemId(request.getItemId());
    witRequest.setDescription(request.getDescription());

    return this.addItemWitClient.call(witRequest);
  }

  @Override
  protected PersistentTransformer<AddItemRequest, EntityPersistent> getPersistentTransformer() {
    return itemPersistentTransformer;
  }



  @Resource
  public void setAddItemWitClient(Client<AddItemWitRequest, AddEntityWitResponse> addItemWitClient) {
    this.addItemWitClient = addItemWitClient;
  }

  @Resource
  public void setItemPersistentTransformer(PersistentTransformer<AddItemRequest, EntityPersistent> itemPersistentTransformer) {
    this.itemPersistentTransformer = itemPersistentTransformer;
  }
}
