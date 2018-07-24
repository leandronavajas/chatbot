package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.AddExpressionWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddExpressionRequest;
import unlp.info.chatbot.transformer.PersistentTransformer;

import javax.annotation.Resource;

@Component
public class AddExpressionOperation extends AbstractAddEntityOperation<AddExpressionRequest, EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddExpressionOperation.class);

  private Client<AddExpressionWitRequest, AddEntityWitResponse> addExpressionWitClient;
  private PersistentTransformer<AddExpressionRequest, EntityPersistent> expressionPersistentTransformer;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }



  @Override
  protected AddEntityWitResponse callWit(AddExpressionRequest request) {
    AddExpressionWitRequest witRequest = new AddExpressionWitRequest();

    witRequest.setEntity(request.getEntity());
    witRequest.setItemId(request.getItemId());
    witRequest.setExpressionId(request.getExpressionId());
    witRequest.setDescription(request.getDescription());

    return this.addExpressionWitClient.call(witRequest);
  }

  @Override
  protected PersistentTransformer<AddExpressionRequest, EntityPersistent> getPersistentTransformer() {
    return this.expressionPersistentTransformer;
  }



  @Resource
  public void setAddExpressionWitClient(Client<AddExpressionWitRequest, AddEntityWitResponse> addExpressionWitClient) {
    this.addExpressionWitClient = addExpressionWitClient;
  }

  @Resource
  public void setExpressionPersistentTransformer(PersistentTransformer<AddExpressionRequest, EntityPersistent> expressionPersistentTransformer) {
    this.expressionPersistentTransformer = expressionPersistentTransformer;
  }
}
