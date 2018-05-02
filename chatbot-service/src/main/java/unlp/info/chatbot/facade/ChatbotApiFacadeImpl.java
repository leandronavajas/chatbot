package unlp.info.chatbot.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddResponseBody;
import unlp.info.chatbot.controller.body.transformer.Transformer;
import unlp.info.chatbot.dto.ResponseDTO;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.AddResponseRequest;
import unlp.info.chatbot.operation.request.GetAllItemsOperationRequest;
import unlp.info.chatbot.operation.request.GetResponseRequest;
import unlp.info.chatbot.operation.request.RemoveResponseRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ChatbotApiFacadeImpl implements ChatbotApiFacade {

  private Operation<GetResponseRequest, ResponseDTO> getResponseOperation;

  private Operation<AddResponseRequest, ResponseDTO> addResponseOperation;
  private Transformer<AddResponseBody, AddResponseRequest> addResponseRequestTransformer;

  private Operation<RemoveResponseRequest, StatusResponse> removeResponseOperation;

  private Operation<GetAllItemsOperationRequest, List<ResponseDTO>> getAllResponsesOperation;

  @Override
  public ResponseDTO getResponse(String entity, BigDecimal confidence) {
    GetResponseRequest request = new GetResponseRequest(entity, confidence);

    return this.getResponseOperation.execute(request);
  }

  @Override
  public ResponseDTO addResponse(AddResponseBody body) {

    AddResponseRequest addResponseRequest = this.addResponseRequestTransformer.transform(body);

    return this.addResponseOperation.execute(addResponseRequest);
  }

  @Override
  public ResponseEntity<StatusResponse> removeResponse(String entity) {

    RemoveResponseRequest removeResponseRequest = new RemoveResponseRequest(entity);

    StatusResponse statusResponse = this.removeResponseOperation.execute(removeResponseRequest);
    return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
  }

  @Override
  public List<ResponseDTO> getAll() {

    return this.getAllResponsesOperation.execute(new GetAllItemsOperationRequest());
  }


  // --- SETTERS ---



  @Resource
  public void setGetResponseOperation(Operation<GetResponseRequest, ResponseDTO> getResponseOperation) {
    this.getResponseOperation = getResponseOperation;
  }

  @Resource
  public void setAddResponseOperation(Operation<AddResponseRequest, ResponseDTO> addResponseOperation) {
    this.addResponseOperation = addResponseOperation;
  }

  @Resource
  public void setAddResponseRequestTransformer(Transformer<AddResponseBody, AddResponseRequest> addResponseRequestTransformer) {
    this.addResponseRequestTransformer = addResponseRequestTransformer;
  }

  @Resource
  public void setRemoveResponseOperation(Operation<RemoveResponseRequest, StatusResponse> removeResponseOperation) {
    this.removeResponseOperation = removeResponseOperation;
  }

  @Resource
  public void setGetAllResponsesOperation(Operation<GetAllItemsOperationRequest, List<ResponseDTO>> getAllResponsesOperation) {
    this.getAllResponsesOperation = getAllResponsesOperation;
  }
}
