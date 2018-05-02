package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddResponseBody;
import unlp.info.chatbot.operation.request.AddResponseRequest;

@Component
public class AddResponseRequestTransformer implements Transformer<AddResponseBody, AddResponseRequest> {

  @Override
  public AddResponseRequest transform(AddResponseBody in) {
    AddResponseRequest addResponseRequest = new AddResponseRequest();

    addResponseRequest.setEntity(in.getEntity());
    addResponseRequest.setDescription(in.getDescription());
    addResponseRequest.setQuickReplies(in.getQuickReplies());
    addResponseRequest.setLinks(in.getLinks());

    return addResponseRequest;
  }
}
