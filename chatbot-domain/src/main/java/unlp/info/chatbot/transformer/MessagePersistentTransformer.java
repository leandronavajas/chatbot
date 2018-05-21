package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.MessagePersistent;
import unlp.info.chatbot.operation.request.AddMessageRequest;

@Component
public class MessagePersistentTransformer implements PersistentTransformer<AddMessageRequest, MessagePersistent> {

  @Override
  public MessagePersistent transform(AddMessageRequest in) {
    MessagePersistent messagePersistent = new MessagePersistent();

    messagePersistent.setId(in.getEntity());
    messagePersistent.setDescription(in.getDescription());
    messagePersistent.setLinks(in.getLinks());

    return messagePersistent;
  }
}
