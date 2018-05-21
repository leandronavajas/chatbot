package unlp.info.chatbot.dto.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.model.MessagePersistent;

@Component
public class MessageDTOTransformer implements DTOTransformer<MessagePersistent, MessageDTO> {

  @Override
  public MessageDTO transform(MessagePersistent in) {

    MessageDTO messageDTO = new MessageDTO();

    messageDTO.setEntity(in.getId());
    messageDTO.setDescription(in.getDescription());
    messageDTO.setLinks(in.getLinks());

    // TODO: LN analizar como transformar los QR
    //messageDTO.setQuickReply(in.getQuickReply());

    return messageDTO;
  }
}
