package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.AddPhraseWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddPhraseOperationRequest;
import unlp.info.chatbot.transformer.PersistentTransformer;

import javax.annotation.Resource;

@Component
public class AddPhraseOperation extends AbstractAddEntityOperation<AddPhraseOperationRequest, EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddPhraseOperation.class);

  private Client<AddPhraseWitRequest, AddEntityWitResponse> addPhraseWitClient;
  private PersistentTransformer<AddPhraseOperationRequest, EntityPersistent> phrasePersistentTransformer;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }


  @Override
  protected AddEntityWitResponse callWit(AddPhraseOperationRequest request) {
    AddPhraseWitRequest witRequest = new AddPhraseWitRequest();
    witRequest.setEntity(request.getEntity());
    witRequest.setDescription(request.getDescription());
    witRequest.setItemId(request.getItemId());

    return this.addPhraseWitClient.call(witRequest);
  }

  @Override
  protected PersistentTransformer<AddPhraseOperationRequest, EntityPersistent> getPersistentTransformer() {
    return this.phrasePersistentTransformer;
  }

  @Resource
  public void setAddPhraseWitClient(Client<AddPhraseWitRequest, AddEntityWitResponse> addPhraseWitClient) {
    this.addPhraseWitClient = addPhraseWitClient;
  }

  @Resource
  public void setPhrasePersistentTransformer(PersistentTransformer<AddPhraseOperationRequest, EntityPersistent> phrasePersistentTransformer) {
    this.phrasePersistentTransformer = phrasePersistentTransformer;
  }
}
