package unlp.info.chatbot.dto.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.model.EntityPersistent;

@Component
public class MessageDTOTransformer implements DTOTransformer<EntityPersistent, MessageDTO> {

  @Override
  public MessageDTO transform(EntityPersistent in) {

    MessageDTO messageDTO = new MessageDTO();

    messageDTO.setEntity(in.getId());
    messageDTO.setDescription(in.getDescription());
    messageDTO.setLinks(in.getLinks());
    messageDTO.setKind(in.getKind());
    messageDTO.setParentId(in.getParentId());
    messageDTO.setWitId(in.getWitId());

    return messageDTO;
  }
}
