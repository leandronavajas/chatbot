package unlp.info.chatbot.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddMessageBody;
import unlp.info.chatbot.controller.body.transformer.Transformer;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.AddMessageRequest;
import unlp.info.chatbot.operation.request.GetAllMessageOperationRequest;
import unlp.info.chatbot.operation.request.GetMessageRequest;
import unlp.info.chatbot.operation.request.RemoveMessageRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ChatbotApiFacadeImpl implements ChatbotApiFacade {

  private Operation<GetMessageRequest, MessageDTO> getMessageOperation;

  private Operation<AddMessageRequest, MessageDTO> addMessageOperation;
  private Transformer<AddMessageBody, AddMessageRequest> addMessageRequestTransformer;

  private Operation<RemoveMessageRequest, StatusResponse> removeMessageOperation;

  private Operation<GetAllMessageOperationRequest, List<MessageDTO>> getAllMessagesOperation;

  @Override
  public MessageDTO getMessage(String entity, BigDecimal confidence) {
    GetMessageRequest request = new GetMessageRequest(entity, confidence);

    return this.getMessageOperation.execute(request);
  }

  @Override
  public MessageDTO addMessage(AddMessageBody body) {

    AddMessageRequest addMessageRequest = this.addMessageRequestTransformer.transform(body);

    return this.addMessageOperation.execute(addMessageRequest);
  }

  @Override
  public ResponseEntity<StatusResponse> removeMessage(String entity) {

    RemoveMessageRequest removeMessageRequest = new RemoveMessageRequest(entity);

    StatusResponse statusResponse = this.removeMessageOperation.execute(removeMessageRequest);
    return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
  }

  @Override
  public List<MessageDTO> getAll() {

    return this.getAllMessagesOperation.execute(new GetAllMessageOperationRequest());
  }


  // --- SETTERS ---



  @Resource
  public void setGetMessageOperation(Operation<GetMessageRequest, MessageDTO> getMessageOperation) {
    this.getMessageOperation = getMessageOperation;
  }

  @Resource
  public void setAddMessageOperation(Operation<AddMessageRequest, MessageDTO> addMessageOperation) {
    this.addMessageOperation = addMessageOperation;
  }

  @Resource
  public void setAddMessageRequestTransformer(Transformer<AddMessageBody, AddMessageRequest> addMessageRequestTransformer) {
    this.addMessageRequestTransformer = addMessageRequestTransformer;
  }

  @Resource
  public void setRemoveMessageOperation(Operation<RemoveMessageRequest, StatusResponse> removeMessageOperation) {
    this.removeMessageOperation = removeMessageOperation;
  }

  @Resource
  public void setGetAllMessagesOperation(Operation<GetAllMessageOperationRequest, List<MessageDTO>> getAllMessagesOperation) {
    this.getAllMessagesOperation = getAllMessagesOperation;
  }
}
