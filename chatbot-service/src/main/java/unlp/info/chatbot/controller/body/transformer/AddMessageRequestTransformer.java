package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.operation.request.AddMessageRequest;

@Component
public class AddMessageRequestTransformer implements RequestTransformer<AddEntityBody, AddMessageRequest> {

  @Override
  public AddMessageRequest transform(AddEntityBody in) {
    AddMessageRequest addMessageRequest = new AddMessageRequest();

    addMessageRequest.setEntity(in.getEntity());
    addMessageRequest.setDescription(in.getDescription());
//    addMessageRequest.setQuickReply(in.getQuickReply());
    addMessageRequest.setLinks(in.getLinks());
    addMessageRequest.setParentId(in.getParentId());

    return addMessageRequest;
  }
}
