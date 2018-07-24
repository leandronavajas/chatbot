package unlp.info.chatbot.facade.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.facade.MessageFacade;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.model.RemoveStatus;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.GetAllMessageOperationRequest;
import unlp.info.chatbot.operation.request.GetMessageRequest;
import unlp.info.chatbot.operation.request.RemoveMessageRequest;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MessageFacadeImpl implements MessageFacade {

  private Operation<GetMessageRequest, EntityPersistent> getMessageOperation;
  private Operation<RemoveMessageRequest, RemoveStatus> removeMessageOperation;
  private Operation<GetAllMessageOperationRequest, List<EntityPersistent>> getAllMessagesOperation;

  private DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer;
  private DTOTransformer<List<EntityPersistent>, List<MessageDTO>> messageDTOListTransformer;


  @Override
  public MessageDTO getMessage(String phrase) {
    GetMessageRequest request = new GetMessageRequest(phrase);

    EntityPersistent entityPersistent = this.getMessageOperation.execute(request);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public ResponseEntity<RemoveStatus> removeMessage(String entity) {

    RemoveMessageRequest removeMessageRequest = new RemoveMessageRequest(entity);

    RemoveStatus statusResponse = this.removeMessageOperation.execute(removeMessageRequest);
    return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
  }

  @Override
  public List<MessageDTO> getAll() {

    List<EntityPersistent> messagesPersistent = this.getAllMessagesOperation.execute(new GetAllMessageOperationRequest());

    return this.messageDTOListTransformer.transform(messagesPersistent);
  }


  // --- SETTERS ---

  @Resource
  public void setGetMessageOperation(Operation<GetMessageRequest, EntityPersistent> getMessageOperation) {
    this.getMessageOperation = getMessageOperation;
  }

  @Resource
  public void setRemoveMessageOperation(Operation<RemoveMessageRequest, RemoveStatus> removeMessageOperation) {
    this.removeMessageOperation = removeMessageOperation;
  }

  @Resource
  public void setGetAllMessagesOperation(Operation<GetAllMessageOperationRequest, List<EntityPersistent>> getAllMessagesOperation) {
    this.getAllMessagesOperation = getAllMessagesOperation;
  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }

  @Resource
  public void setMessageDTOListTransformer(DTOTransformer<List<EntityPersistent>, List<MessageDTO>> messageDTOListTransformer) {
    this.messageDTOListTransformer = messageDTOListTransformer;
  }
}
