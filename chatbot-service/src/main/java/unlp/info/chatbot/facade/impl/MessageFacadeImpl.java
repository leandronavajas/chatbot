package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.facade.MessageFacade;
import unlp.info.chatbot.model.Entity;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.GetMessageRequest;

import javax.annotation.Resource;

@Component
public class MessageFacadeImpl implements MessageFacade {

  private Operation<GetMessageRequest, Entity> getMessageOperation;

  private DTOTransformer<Entity, MessageDTO> messageDTOTransformer;


  @Override
  public MessageDTO getMessage(String phrase) {
    GetMessageRequest request = new GetMessageRequest(phrase);

    Entity entity = this.getMessageOperation.execute(request);

    return this.messageDTOTransformer.transform(entity);
  }


  @Resource
  public void setGetMessageOperation(Operation<GetMessageRequest, Entity> getMessageOperation) {
    this.getMessageOperation = getMessageOperation;
  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<Entity, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }

}
