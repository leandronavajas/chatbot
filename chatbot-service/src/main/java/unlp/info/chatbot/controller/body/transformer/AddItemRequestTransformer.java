package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddItemPathAndBody;
import unlp.info.chatbot.operation.request.AddItemRequest;

@Component
public class AddItemRequestTransformer implements RequestTransformer<AddItemPathAndBody, AddItemRequest> {

  @Override
  public AddItemRequest transform(AddItemPathAndBody in) {
    AddItemRequest addItemRequest = new AddItemRequest();

    addItemRequest.setEntity(in.getCategoryId());
    addItemRequest.setDescription(in.getBody().getDescription());
    addItemRequest.setLinks(in.getBody().getLinks());
    addItemRequest.setItemId(in.getBody().getName());

    return addItemRequest;
  }
}
