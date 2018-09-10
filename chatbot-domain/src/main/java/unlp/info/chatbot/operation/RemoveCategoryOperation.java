package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.RemoveCategoryWitClientRequest;
import unlp.info.chatbot.client.response.RemoveEntityWitClientResponse;
import unlp.info.chatbot.operation.request.RemoveCategoryOperationRequest;

import javax.annotation.Resource;

@Component
public class RemoveCategoryOperation extends AbstractRemoveEntityOperation<RemoveCategoryOperationRequest> {

  private Client<RemoveCategoryWitClientRequest, RemoveEntityWitClientResponse> removeCategoryWitClient;

  @Override
  protected void callWit(RemoveCategoryOperationRequest request) {
    RemoveCategoryWitClientRequest witRequest = new RemoveCategoryWitClientRequest(request.getCategoryId());
    this.removeCategoryWitClient.call(witRequest);
  }

  @Resource
  public void setRemoveCategoryWitClient(Client<RemoveCategoryWitClientRequest, RemoveEntityWitClientResponse> removeCategoryWitClient) {
    this.removeCategoryWitClient = removeCategoryWitClient;
  }
}
