package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.operation.request.AddMessageRequest;

@Component
public class MessageDTOTransformer implements Transformer<AddMessageRequest, MessageDTO> {

  @Override
  public MessageDTO transform(AddMessageRequest in) {

    MessageDTO messageDTO = new MessageDTO();

    messageDTO.setEntity(in.getEntity());
    messageDTO.setDescription(in.getDescription());
    messageDTO.setQuickReply(in.getQuickReply());
    messageDTO.setLinks(in.getLinks());

    return messageDTO;
  }
}
