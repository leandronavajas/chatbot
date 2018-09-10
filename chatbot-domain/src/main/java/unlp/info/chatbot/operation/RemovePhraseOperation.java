package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.RemovePhraseWitClientRequest;
import unlp.info.chatbot.client.response.RemovePhraseWitClientResponse;
import unlp.info.chatbot.operation.request.RemovePhraseOperationRequest;

import javax.annotation.Resource;

@Component
public class RemovePhraseOperation extends AbstractRemoveEntityOperation<RemovePhraseOperationRequest>  {

  private Client<RemovePhraseWitClientRequest, RemovePhraseWitClientResponse> removePhraseWitClient;

  @Override
  protected void callWit(RemovePhraseOperationRequest request) {
    RemovePhraseWitClientRequest witRequest = new RemovePhraseWitClientRequest(request.getCategoryId(), request.getItemId(), request.getPhrase());
    this.removePhraseWitClient.call(witRequest);
  }

  @Resource
  public void setRemovePhraseWitClient(Client<RemovePhraseWitClientRequest, RemovePhraseWitClientResponse> removePhraseWitClient) {
    this.removePhraseWitClient = removePhraseWitClient;
  }
}
