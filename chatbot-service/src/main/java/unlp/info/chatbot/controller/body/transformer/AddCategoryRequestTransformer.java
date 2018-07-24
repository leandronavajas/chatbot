package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.operation.request.AddCategoryRequest;

@Component
public class AddCategoryRequestTransformer implements RequestTransformer<AddEntityBody, AddCategoryRequest> {

  @Override
  public AddCategoryRequest transform(AddEntityBody in) {
    AddCategoryRequest addCategoryRequest = new AddCategoryRequest();

    addCategoryRequest.setEntity(in.getName());
    addCategoryRequest.setDescription(in.getDescription());
    addCategoryRequest.setLinks(in.getLinks());
    addCategoryRequest.setParentId(in.getParentId());

    return addCategoryRequest;
  }
}
