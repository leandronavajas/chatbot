package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.operation.request.AddCategoryOperationRequest;

@Component
public class AddCategoryRequestTransformer implements RequestTransformer<AddEntityBody, AddCategoryOperationRequest> {

  @Override
  public AddCategoryOperationRequest transform(AddEntityBody in) {
    AddCategoryOperationRequest addCategoryOperationRequest = new AddCategoryOperationRequest();

    addCategoryOperationRequest.setEntity(in.getName());
    addCategoryOperationRequest.setDescription(in.getDescription());
    addCategoryOperationRequest.setLinks(in.getLinks());
    addCategoryOperationRequest.setParentId(in.getParentId());

    return addCategoryOperationRequest;
  }
}
