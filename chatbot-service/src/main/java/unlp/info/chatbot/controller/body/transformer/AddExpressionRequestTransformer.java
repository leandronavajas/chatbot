package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddExpressionPathAndBody;
import unlp.info.chatbot.operation.request.AddExpressionRequest;

@Component
public class AddExpressionRequestTransformer implements  RequestTransformer<AddExpressionPathAndBody, AddExpressionRequest> {

  @Override
  public AddExpressionRequest transform(AddExpressionPathAndBody in) {
    AddExpressionRequest addExpressionRequest = new AddExpressionRequest();

    addExpressionRequest.setEntity(in.getCategoryId());
    addExpressionRequest.setLinks(in.getBody().getLinks());
    addExpressionRequest.setItemId(in.getItemId());
    addExpressionRequest.setExpressionId(in.getBody().getDescription());

    return addExpressionRequest;
  }

}
