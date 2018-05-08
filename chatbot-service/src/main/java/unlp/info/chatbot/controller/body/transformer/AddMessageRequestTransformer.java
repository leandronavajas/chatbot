package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddMessageBody;
import unlp.info.chatbot.operation.request.AddMessageRequest;

@Component
public class AddMessageRequestTransformer implements Transformer<AddMessageBody, AddMessageRequest> {

  @Override
  public AddMessageRequest transform(AddMessageBody in) {
    AddMessageRequest addMessageRequest = new AddMessageRequest();

    addMessageRequest.setEntity(in.getEntity());
    addMessageRequest.setDescription(in.getDescription());
    addMessageRequest.setQuickReplies(in.getQuickReplies());
    addMessageRequest.setLinks(in.getLinks());

    return addMessageRequest;
  }
}