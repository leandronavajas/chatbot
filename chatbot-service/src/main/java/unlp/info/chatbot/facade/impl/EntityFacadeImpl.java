package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.controller.body.transformer.RequestTransformer;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.facade.EntityFacade;
import unlp.info.chatbot.model.MessagePersistent;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.AddMessageRequest;

import javax.annotation.Resource;

@Component
public class EntityFacadeImpl implements EntityFacade {

  private RequestTransformer<AddEntityBody, AddMessageRequest> addMessageRequestTransformer;
  private Operation<AddMessageRequest, MessagePersistent> addEntityOperation;
  private DTOTransformer<MessagePersistent, MessageDTO> messageDTOTransformer;

  @Override
  public MessageDTO addEntity(AddEntityBody body) {

    AddMessageRequest addMessageRequest = this.addMessageRequestTransformer.transform(body);

    MessagePersistent messagePersistent = this.addEntityOperation.execute(addMessageRequest);

    return this.messageDTOTransformer.transform(messagePersistent);
  }


  @Resource
  public void setAddMessageRequestTransformer(RequestTransformer<AddEntityBody, AddMessageRequest> addMessageRequestTransformer) {
    this.addMessageRequestTransformer = addMessageRequestTransformer;
  }

  @Resource
  public void setAddEntityOperation(Operation<AddMessageRequest, MessagePersistent> addEntityOperation) {
    this.addEntityOperation = addEntityOperation;
  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<MessagePersistent, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }
}
