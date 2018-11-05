package unlp.info.chatbot.dto.transformer;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.model.Entity;
import unlp.info.chatbot.model.EntityPersistent;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MessageDTOListTransformer implements DTOTransformer<List<Entity>, List<MessageDTO>> {


  private DTOTransformer<Entity, MessageDTO> messageDTOTransformer;

  @Override
  public List<MessageDTO> transform(List<Entity> in) {

    List<MessageDTO> response = Lists.newArrayList();
    for (Entity entity: in) {
      MessageDTO messageDTO = this.messageDTOTransformer.transform(entity);
      response.add(messageDTO);
    }
    return response;

  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<Entity, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }
}
