package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddItemPathAndBody;
import unlp.info.chatbot.operation.request.AddItemOperationRequest;

@Component
public class AddItemRequestTransformer implements RequestTransformer<AddItemPathAndBody, AddItemOperationRequest> {

  @Override
  public AddItemOperationRequest transform(AddItemPathAndBody in) {
    AddItemOperationRequest addItemRequest = new AddItemOperationRequest();

    addItemRequest.setEntity(in.getCategoryId());
    addItemRequest.setDescription(in.getBody().getDescription());
    addItemRequest.setLinks(in.getBody().getLinks());
    addItemRequest.setItemId(in.getBody().getName());

    return addItemRequest;
  }
}
