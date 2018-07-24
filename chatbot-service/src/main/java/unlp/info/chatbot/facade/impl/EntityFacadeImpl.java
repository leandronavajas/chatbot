package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.request.AddValueEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.controller.body.AddExpressionPathAndBody;
import unlp.info.chatbot.controller.body.AddItemPathAndBody;
import unlp.info.chatbot.controller.body.transformer.RequestTransformer;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.facade.EntityFacade;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.AddCategoryRequest;
import unlp.info.chatbot.operation.request.AddExpressionRequest;
import unlp.info.chatbot.operation.request.AddItemRequest;
import unlp.info.chatbot.operation.request.AddValueEntityOperationRequest;

import javax.annotation.Resource;

@Component
public class EntityFacadeImpl implements EntityFacade {

  private RequestTransformer<AddEntityBody, AddCategoryRequest> addCategoryRequestTransformer;
  private RequestTransformer<AddItemPathAndBody, AddItemRequest> addItemRequestTransformer;
  private RequestTransformer<AddExpressionPathAndBody, AddExpressionRequest> addExpressionRequestTransformer;

  private Operation<AddCategoryRequest, EntityPersistent> addCategoryOperation;
  private Operation<AddItemRequest, EntityPersistent> addItemOperation;
  private Operation<AddExpressionRequest, EntityPersistent> addExpressionOperation;

  private Operation<AddCategoryRequest, EntityPersistent> addEntityOperation;
  private DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer;

  private Operation<AddValueEntityOperationRequest, AddEntityWitResponse> addValueEntityOperation;

  @Override
  public MessageDTO addCategory(AddEntityBody body) {

    AddCategoryRequest addCategoryRequest = this.addCategoryRequestTransformer.transform(body);

    EntityPersistent entityPersistent = this.addCategoryOperation.execute(addCategoryRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public MessageDTO addItem(String categoryId, AddEntityBody body) {

    AddItemPathAndBody addItemPathAndBody = new AddItemPathAndBody(categoryId, body);

    AddItemRequest addItemRequest = this.addItemRequestTransformer.transform(addItemPathAndBody);

    EntityPersistent entityPersistent = this.addItemOperation.execute(addItemRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public MessageDTO addExpression(String categoryId, String itemId, AddEntityBody body) {

    AddExpressionPathAndBody addExpressionPathAndBody = new AddExpressionPathAndBody(categoryId, itemId, body);

    AddExpressionRequest addExpressionRequest = this.addExpressionRequestTransformer.transform(addExpressionPathAndBody);

    EntityPersistent entityPersistent = this.addExpressionOperation.execute(addExpressionRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public AddEntityWitResponse addValueForEntity(String entity, AddValueEntityWitRequest body) {

    AddValueEntityOperationRequest request = new AddValueEntityOperationRequest();
    request.setEntity(entity);
    request.setBody(body);

    return this.addValueEntityOperation.execute(request);
  }


  @Resource
  public void setAddCategoryRequestTransformer(RequestTransformer<AddEntityBody, AddCategoryRequest> addCategoryRequestTransformer) {
    this.addCategoryRequestTransformer = addCategoryRequestTransformer;
  }

  @Resource
  public void setAddItemRequestTransformer(RequestTransformer<AddItemPathAndBody, AddItemRequest> addItemRequestTransformer) {
    this.addItemRequestTransformer = addItemRequestTransformer;
  }

  @Resource
  public void setAddExpressionRequestTransformer(RequestTransformer<AddExpressionPathAndBody, AddExpressionRequest> addExpressionRequestTransformer) {
    this.addExpressionRequestTransformer = addExpressionRequestTransformer;
  }

  @Resource
  public void setAddEntityOperation(Operation<AddCategoryRequest, EntityPersistent> addEntityOperation) {
    this.addEntityOperation = addEntityOperation;
  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }

  @Resource
  public void setAddValueEntityOperation(Operation<AddValueEntityOperationRequest, AddEntityWitResponse> addValueEntityOperation) {
    this.addValueEntityOperation = addValueEntityOperation;
  }

  @Resource
  public void setAddCategoryOperation(Operation<AddCategoryRequest, EntityPersistent> addCategoryOperation) {
    this.addCategoryOperation = addCategoryOperation;
  }

  @Resource
  public void setAddItemOperation(Operation<AddItemRequest, EntityPersistent> addItemOperation) {
    this.addItemOperation = addItemOperation;
  }

  @Resource
  public void setAddExpressionOperation(Operation<AddExpressionRequest, EntityPersistent> addExpressionOperation) {
    this.addExpressionOperation = addExpressionOperation;
  }
}
