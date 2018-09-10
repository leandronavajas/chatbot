package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.RemoveItemWitClientRequest;
import unlp.info.chatbot.client.response.RemoveEntityWitClientResponse;
import unlp.info.chatbot.operation.request.RemoveItemOperationRequest;

import javax.annotation.Resource;

@Component
public class RemoveItemOperation extends AbstractRemoveEntityOperation<RemoveItemOperationRequest> {

  private Client<RemoveItemWitClientRequest, RemoveEntityWitClientResponse> removeItemWitClient;

  @Override
  protected void callWit(RemoveItemOperationRequest request) {
    RemoveItemWitClientRequest witRequest = new RemoveItemWitClientRequest(request.getCategoryId(), request.getItemId());
    this.removeItemWitClient.call(witRequest);
  }

  @Resource
  public void setRemoveItemWitClient(Client<RemoveItemWitClientRequest, RemoveEntityWitClientResponse> removeItemWitClient) {
    this.removeItemWitClient = removeItemWitClient;
  }
}
