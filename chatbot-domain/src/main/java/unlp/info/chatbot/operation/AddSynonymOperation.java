package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.AddSynonymWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddSynonymRequest;
import unlp.info.chatbot.transformer.PersistentTransformer;

import javax.annotation.Resource;

@Component
public class AddSynonymOperation extends AbstractAddEntityOperation<AddSynonymRequest, EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddSynonymOperation.class);

  private Client<AddSynonymWitRequest, AddEntityWitResponse> addSynonymWitClient;
  private PersistentTransformer<AddSynonymRequest, EntityPersistent> synonymPersistentTransformer;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }



  @Override
  protected AddEntityWitResponse callWit(AddSynonymRequest request) {
    AddSynonymWitRequest witRequest = new AddSynonymWitRequest();

    witRequest.setEntity(request.getEntity());
    witRequest.setItemId(request.getItemId());
    witRequest.setSynonymId(request.getSynonymId());
    witRequest.setDescription(request.getDescription());

    return this.addSynonymWitClient.call(witRequest);
  }

  @Override
  protected PersistentTransformer<AddSynonymRequest, EntityPersistent> getPersistentTransformer() {
    return this.synonymPersistentTransformer;
  }



  @Resource
  public void setAddSynonymWitClient(Client<AddSynonymWitRequest, AddEntityWitResponse> addSynonymWitClient) {
    this.addSynonymWitClient = addSynonymWitClient;
  }

  @Resource
  public void setSynonymPersistentTransformer(PersistentTransformer<AddSynonymRequest, EntityPersistent> synonymPersistentTransformer) {
    this.synonymPersistentTransformer = synonymPersistentTransformer;
  }
}
