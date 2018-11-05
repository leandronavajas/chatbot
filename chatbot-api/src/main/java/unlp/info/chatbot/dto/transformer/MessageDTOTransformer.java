package unlp.info.chatbot.dto.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.QuickReplyDTO;
import unlp.info.chatbot.model.Entity;
import unlp.info.chatbot.model.EntityPersistent;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MessageDTOTransformer implements DTOTransformer<Entity, MessageDTO> {

  private DTOTransformer<List<String>, List<QuickReplyDTO>> quickReplyDTOTransformer;

  @Override
  public MessageDTO transform(Entity in) {

    MessageDTO messageDTO = new MessageDTO();

    messageDTO.setEntity(in.getId());
    messageDTO.setDescription(in.getDescription());
    messageDTO.setLinks(in.getLinks());
    messageDTO.setKind(in.getKind());
    messageDTO.setParentId(in.getParentId());
    messageDTO.setWitId(in.getWitId());
    messageDTO.setQuickReply(this.quickReplyDTOTransformer.transform(in.getChildren()));

    return messageDTO;
  }

  @Resource
  public void setQuickReplyDTOTransformer(DTOTransformer<List<String>, List<QuickReplyDTO>> quickReplyDTOTransformer) {
    this.quickReplyDTOTransformer = quickReplyDTOTransformer;
  }
}
