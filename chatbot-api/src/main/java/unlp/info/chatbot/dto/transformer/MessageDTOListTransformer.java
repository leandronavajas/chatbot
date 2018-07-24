package unlp.info.chatbot.dto.transformer;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.model.EntityPersistent;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MessageDTOListTransformer implements DTOTransformer<List<EntityPersistent>, List<MessageDTO>> {

  private DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer;

  @Override
  public List<MessageDTO> transform(List<EntityPersistent> in) {

    List<MessageDTO> response = Lists.newArrayList();
    for (EntityPersistent entityPersistent : in) {
      MessageDTO messageDTO = this.messageDTOTransformer.transform(entityPersistent);
      response.add(messageDTO);
    }
    return response;

  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }
}
