package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.dto.transformer.request.StatusResponseDTOTransformerRequest;
import unlp.info.chatbot.facade.RemoveEntityFacade;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.RemoveCategoryOperationRequest;
import unlp.info.chatbot.operation.request.RemoveItemOperationRequest;
import unlp.info.chatbot.operation.request.RemovePhraseOperationRequest;
import unlp.info.chatbot.operation.request.RemoveSynonymOperationRequest;

import javax.annotation.Resource;

@Component
public class RemoveEntityFacadeImpl implements RemoveEntityFacade {

  private Operation<RemoveCategoryOperationRequest, String> removeCategoryOperation;
  private Operation<RemoveItemOperationRequest, String> removeItemOperation;
  private Operation<RemoveSynonymOperationRequest, String> removeSynonymOperation;
  private Operation<RemovePhraseOperationRequest, String> removePhraseOperation;

  private DTOTransformer<StatusResponseDTOTransformerRequest, StatusResponse> statusResponseDTOTransformer;



  @Override
  public StatusResponse removeCategory(String categoryId) {

    RemoveCategoryOperationRequest request = new RemoveCategoryOperationRequest(categoryId);
    String status = this.removeCategoryOperation.execute(request);

    StatusResponseDTOTransformerRequest dtoTransformerRequest = new StatusResponseDTOTransformerRequest(status, categoryId);
    return this.statusResponseDTOTransformer.transform(dtoTransformerRequest);
  }

  @Override
  public StatusResponse removeItem(String categoryId, String itemId) {
    RemoveItemOperationRequest request = new RemoveItemOperationRequest(categoryId, itemId);
    String status = this.removeItemOperation.execute(request);

    StatusResponseDTOTransformerRequest dtoTransformerRequest = new StatusResponseDTOTransformerRequest(status, itemId);
    return this.statusResponseDTOTransformer.transform(dtoTransformerRequest);
  }

  @Override
  public StatusResponse removeSynonym(String categoryId, String itemId, String synonymId) {
    RemoveSynonymOperationRequest request = new RemoveSynonymOperationRequest(categoryId, itemId, synonymId);
    String status = this.removeSynonymOperation.execute(request);

    StatusResponseDTOTransformerRequest dtoTransformerRequest = new StatusResponseDTOTransformerRequest(status, synonymId);
    return this.statusResponseDTOTransformer.transform(dtoTransformerRequest);
  }

  @Override
  public StatusResponse removePhrase(String categoryId, String itemId, String phrase) {
    RemovePhraseOperationRequest request = new RemovePhraseOperationRequest(categoryId, itemId, phrase);
    String status = this.removePhraseOperation.execute(request);

    StatusResponseDTOTransformerRequest dtoTransformerRequest = new StatusResponseDTOTransformerRequest(status, phrase);
    return this.statusResponseDTOTransformer.transform(dtoTransformerRequest);
  }



  @Resource
  public void setRemoveCategoryOperation(Operation<RemoveCategoryOperationRequest, String> removeCategoryOperation) {
    this.removeCategoryOperation = removeCategoryOperation;
  }

  @Resource
  public void setRemoveItemOperation(Operation<RemoveItemOperationRequest, String> removeItemOperation) {
    this.removeItemOperation = removeItemOperation;
  }

  @Resource
  public void setRemoveSynonymOperation(Operation<RemoveSynonymOperationRequest, String> removeSynonymOperation) {
    this.removeSynonymOperation = removeSynonymOperation;
  }

  @Resource
  public void setRemovePhraseOperation(Operation<RemovePhraseOperationRequest, String> removePhraseOperation) {
    this.removePhraseOperation = removePhraseOperation;
  }

  @Resource
  public void setStatusResponseDTOTransformer(DTOTransformer<StatusResponseDTOTransformerRequest, StatusResponse> statusResponseDTOTransformer) {
    this.statusResponseDTOTransformer = statusResponseDTOTransformer;
  }
}
