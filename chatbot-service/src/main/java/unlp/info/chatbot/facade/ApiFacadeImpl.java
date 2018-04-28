package unlp.info.chatbot.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddItemBody;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.dto.TestDTO;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.GetAllItemsOperationRequest;
import unlp.info.chatbot.operation.request.GetItemOperationRequest;
import unlp.info.chatbot.operation.request.RemoveItemOperationRequest;
import unlp.info.chatbot.operation.request.SaveItemOperationRequest;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ApiFacadeImpl implements ApiFacade {

  private Operation<GetItemOperationRequest, TestDTO> getItemOperation;

  private Operation<SaveItemOperationRequest, TestDTO> saveItemOperation;

  private Operation<RemoveItemOperationRequest, StatusResponse> removeItemOperation;

  private Operation<GetAllItemsOperationRequest, List<TestDTO>> getAllItemsOperation;

  public TestDTO get(String id) {
    GetItemOperationRequest request = new GetItemOperationRequest(id);
    return this.getItemOperation.execute(request);
  }

  public TestDTO add(AddItemBody body) {
    SaveItemOperationRequest request = new SaveItemOperationRequest(body.getId(), body.getDescription());
    TestDTO response = this.saveItemOperation.execute(request);

    return response;
  }

  public ResponseEntity<StatusResponse> remove(String id) {
    RemoveItemOperationRequest request = new RemoveItemOperationRequest(id);

    StatusResponse statusResponse = this.removeItemOperation.execute(request);
    return ResponseEntity.status(HttpStatus.OK).body(statusResponse);
  }

  public List<TestDTO> get() {
    GetAllItemsOperationRequest request = new GetAllItemsOperationRequest();
    return this.getAllItemsOperation.execute(request);
  }

  @Resource
  public void setGetItemOperation(Operation<GetItemOperationRequest, TestDTO> getItemOperation) {
    this.getItemOperation = getItemOperation;
  }

  @Resource
  public void setSaveItemOperation(Operation<SaveItemOperationRequest, TestDTO> saveItemOperation) {
    this.saveItemOperation = saveItemOperation;
  }

  @Resource
  public void setRemoveItemOperation(Operation<RemoveItemOperationRequest, StatusResponse> removeItemOperation) {
    this.removeItemOperation = removeItemOperation;
  }

  @Resource
  public void setGetAllItemsOperation(Operation<GetAllItemsOperationRequest, List<TestDTO>> getAllItemsOperation) {
    this.getAllItemsOperation = getAllItemsOperation;
  }
}
