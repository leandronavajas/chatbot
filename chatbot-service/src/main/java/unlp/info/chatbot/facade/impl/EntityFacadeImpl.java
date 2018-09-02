package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.controller.body.AddSynonymPathAndBody;
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
  private RequestTransformer<AddSynonymPathAndBody, AddSynonymRequest> addSynonymRequestTransformer;

  private Operation<AddCategoryRequest, EntityPersistent> addCategoryOperation;
  private Operation<AddItemRequest, EntityPersistent> addItemOperation;
  private Operation<AddSynonymRequest, EntityPersistent> addSynonymOperation;

  private Operation<GetAllEntitiesRequest, List<EntityPersistent>> getAllEntitiesOperation;
  private Operation<GetItemsForCategoryRequest, List<EntityPersistent>> getItemsForCategoryOperation;
  private Operation<GetSynonymsForItemRequest, List<EntityPersistent>> getSynonymsForItemOperation;

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
  public MessageDTO addSynonym(String categoryId, String itemId, AddEntityBody body) {

    AddSynonymPathAndBody addSynonymPathAndBody = new AddSynonymPathAndBody(categoryId, itemId, body);

    AddSynonymRequest addSynonymRequest = this.addSynonymRequestTransformer.transform(addSynonymPathAndBody);

    EntityPersistent entityPersistent = this.addSynonymOperation.execute(addSynonymRequest);

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
  public List<MessageDTO> getSynonymsForItem(String categoryId, String itemId) {
    GetSynonymsForItemRequest request = new GetSynonymsForItemRequest(categoryId, itemId);

    List<EntityPersistent> entities = this.getSynonymsForItemOperation.execute(request);

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
  public void setAddSynonymRequestTransformer(RequestTransformer<AddSynonymPathAndBody, AddSynonymRequest> addSynonymRequestTransformer) {
    this.addSynonymRequestTransformer = addSynonymRequestTransformer;
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
  public void setAddSynonymOperation(Operation<AddSynonymRequest, EntityPersistent> addSynonymOperation) {
    this.addSynonymOperation = addSynonymOperation;
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
  public void setGetSynonymsForItemOperation(Operation<GetSynonymsForItemRequest, List<EntityPersistent>> getSynonymsForItemOperation) {
    this.getSynonymsForItemOperation = getSynonymsForItemOperation;
  }
}
