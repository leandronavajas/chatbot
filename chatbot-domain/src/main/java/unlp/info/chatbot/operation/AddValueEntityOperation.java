package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.operation.request.AddValueEntityOperationRequest;

import javax.annotation.Resource;

@Component
public class AddValueEntityOperation implements Operation<AddValueEntityOperationRequest, AddEntityWitResponse> {

  private Client<AddValueEntityOperationRequest, AddEntityWitResponse> addValueEntityWitClient;

  @Override
  public AddEntityWitResponse execute(AddValueEntityOperationRequest request) {
    return this.addValueEntityWitClient.call(request);
  }

  @Resource
  public void setAddValueEntityWitClient(Client<AddValueEntityOperationRequest, AddEntityWitResponse> addValueEntityWitClient) {
    this.addValueEntityWitClient = addValueEntityWitClient;
  }
}
