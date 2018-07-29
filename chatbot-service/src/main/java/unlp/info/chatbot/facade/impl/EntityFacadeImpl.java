package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.controller.body.AddExpressionPathAndBody;
import unlp.info.chatbot.controller.body.AddItemPathAndBody;
import unlp.info.chatbot.operation.request.*;
import unlp.info.chatbot.controller.body.transformer.RequestTransformer;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.facade.EntityFacade;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.Operation;

import javax.annotation.Resource;
import java.util.List;

@Component
public class EntityFacadeImpl implements EntityFacade {

  private RequestTransformer<AddEntityBody, AddCategoryRequest> addCategoryRequestTransformer;
  private RequestTransformer<AddItemPathAndBody, AddItemRequest> addItemRequestTransformer;
  private RequestTransformer<AddExpressionPathAndBody, AddExpressionRequest> addExpressionRequestTransformer;

  private Operation<AddCategoryRequest, EntityPersistent> addCategoryOperation;
  private Operation<AddItemRequest, EntityPersistent> addItemOperation;
  private Operation<AddExpressionRequest, EntityPersistent> addExpressionOperation;

  private Operation<GetAllEntitiesRequest, List<EntityPersistent>> getAllEntitiesOperation;
  private Operation<GetItemsForCategoryRequest, List<EntityPersistent>> getItemsForCategoryOperation;
  private Operation<GetExpressionsForItemRequest, List<EntityPersistent>> getExpressionsForItemOperation;

  private DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer;
  private DTOTransformer<List<EntityPersistent>, List<MessageDTO>> messageDTOListTransformer;

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
  public List<MessageDTO> getAll(String filter) {

    GetAllEntitiesRequest request = new GetAllEntitiesRequest(filter);

    List<EntityPersistent> entities = this.getAllEntitiesOperation.execute(request);

    return this.messageDTOListTransformer.transform(entities);
  }

  @Override
  public List<MessageDTO> getItemsForCategory(String categoryId) {
    GetItemsForCategoryRequest request = new GetItemsForCategoryRequest(categoryId);

    List<EntityPersistent> entities = this.getItemsForCategoryOperation.execute(request);

    return this.messageDTOListTransformer.transform(entities);
  }

  @Override
  public List<MessageDTO> getExpressionsForItem(String categoryId, String itemId) {
    GetExpressionsForItemRequest request = new GetExpressionsForItemRequest(categoryId, itemId);

    List<EntityPersistent> entities = this.getExpressionsForItemOperation.execute(request);

    return this.messageDTOListTransformer.transform(entities);
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
  public void setMessageDTOTransformer(DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
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

  @Resource
  public void setGetAllEntitiesOperation(Operation<GetAllEntitiesRequest, List<EntityPersistent>> getAllEntitiesOperation) {
    this.getAllEntitiesOperation = getAllEntitiesOperation;
  }

  @Resource
  public void setMessageDTOListTransformer(DTOTransformer<List<EntityPersistent>, List<MessageDTO>> messageDTOListTransformer) {
    this.messageDTOListTransformer = messageDTOListTransformer;
  }

  @Resource
  public void setGetItemsForCategoryOperation(Operation<GetItemsForCategoryRequest, List<EntityPersistent>> getItemsForCategoryOperation) {
    this.getItemsForCategoryOperation = getItemsForCategoryOperation;
  }

  @Resource
  public void setGetExpressionsForItemOperation(Operation<GetExpressionsForItemRequest, List<EntityPersistent>> getExpressionsForItemOperation) {
    this.getExpressionsForItemOperation = getExpressionsForItemOperation;
  }
}
