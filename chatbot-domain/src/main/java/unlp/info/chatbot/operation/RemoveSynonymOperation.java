package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.RemoveSynonymWitClientRequest;
import unlp.info.chatbot.client.response.RemoveSynonymWitClientResponse;
import unlp.info.chatbot.operation.request.RemoveSynonymOperationRequest;

import javax.annotation.Resource;

@Component
public class RemoveSynonymOperation extends AbstractRemoveEntityOperation<RemoveSynonymOperationRequest>  {

  private Client<RemoveSynonymWitClientRequest, RemoveSynonymWitClientResponse> removeSynonymWitClient;

  @Override
  protected void callWit(RemoveSynonymOperationRequest request) {
    RemoveSynonymWitClientRequest witRequest = new RemoveSynonymWitClientRequest(request.getCategoryId(), request.getItemId(), request.getSynonymId());
    this.removeSynonymWitClient.call(witRequest);
  }

  @Resource
  public void setRemoveSynonymWitClient(Client<RemoveSynonymWitClientRequest, RemoveSynonymWitClientResponse> removeSynonymWitClient) {
    this.removeSynonymWitClient = removeSynonymWitClient;
  }
}
