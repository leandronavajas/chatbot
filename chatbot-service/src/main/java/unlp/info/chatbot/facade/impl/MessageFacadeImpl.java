package unlp.info.chatbot.facade.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.controller.body.transformer.RequestTransformer;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.facade.MessageFacade;
import unlp.info.chatbot.model.MessagePersistent;
import unlp.info.chatbot.model.RemoveStatus;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.AddMessageRequest;
import unlp.info.chatbot.operation.request.GetAllMessageOperationRequest;
import unlp.info.chatbot.operation.request.GetMessageRequest;
import unlp.info.chatbot.operation.request.RemoveMessageRequest;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MessageFacadeImpl implements MessageFacade {

  private Operation<GetMessageRequest, MessagePersistent> getMessageOperation;
  private Operation<RemoveMessageRequest, RemoveStatus> removeMessageOperation;
  private Operation<GetAllMessageOperationRequest, List<MessagePersistent>> getAllMessagesOperation;

  private DTOTransformer<MessagePersistent, MessageDTO> messageDTOTransformer;
  private DTOTransformer<List<MessagePersistent>, List<MessageDTO>> messageDTOListTransformer;


  @Override
  public MessageDTO getMessage(String phrase) {
    GetMessageRequest request = new GetMessageRequest(phrase);

    MessagePersistent messagePersistent = this.getMessageOperation.execute(request);

    return this.messageDTOTransformer.transform(messagePersistent);
  }

  @Override
  public ResponseEntity<RemoveStatus> removeMessage(String entity) {

    RemoveMessageRequest removeMessageRequest = new RemoveMessageRequest(entity);

    RemoveStatus statusResponse = this.removeMessageOperation.execute(removeMessageRequest);
    return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
  }

  @Override
  public List<MessageDTO> getAll() {

    List<MessagePersistent> messagesPersistent = this.getAllMessagesOperation.execute(new GetAllMessageOperationRequest());

    return this.messageDTOListTransformer.transform(messagesPersistent);
  }


  // --- SETTERS ---

  @Resource
  public void setGetMessageOperation(Operation<GetMessageRequest, MessagePersistent> getMessageOperation) {
    this.getMessageOperation = getMessageOperation;
  }

  @Resource
  public void setRemoveMessageOperation(Operation<RemoveMessageRequest, RemoveStatus> removeMessageOperation) {
    this.removeMessageOperation = removeMessageOperation;
  }

  @Resource
  public void setGetAllMessagesOperation(Operation<GetAllMessageOperationRequest, List<MessagePersistent>> getAllMessagesOperation) {
    this.getAllMessagesOperation = getAllMessagesOperation;
  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<MessagePersistent, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }

  @Resource
  public void setMessageDTOListTransformer(DTOTransformer<List<MessagePersistent>, List<MessageDTO>> messageDTOListTransformer) {
    this.messageDTOListTransformer = messageDTOListTransformer;
  }
}
