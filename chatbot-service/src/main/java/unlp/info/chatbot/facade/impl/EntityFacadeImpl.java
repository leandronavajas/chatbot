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

  private RequestTransformer<AddEntityBody, AddCategoryOperationRequest> addCategoryRequestTransformer;
  private RequestTransformer<AddItemPathAndBody, AddItemOperationRequest> addItemRequestTransformer;
  private RequestTransformer<AddSynonymPathAndBody, AddSynonymOperationRequest> addSynonymRequestTransformer;

  private Operation<AddCategoryOperationRequest, EntityPersistent> addCategoryOperation;
  private Operation<AddItemOperationRequest, EntityPersistent> addItemOperation;
  private Operation<AddSynonymOperationRequest, EntityPersistent> addSynonymOperation;
  private Operation<AddPhraseOperationRequest, EntityPersistent> addPhraseOperation;

  private Operation<GetAllEntitiesRequest, List<EntityPersistent>> getAllEntitiesOperation;
  private Operation<GetItemsForCategoryRequest, List<EntityPersistent>> getItemsForCategoryOperation;
  private Operation<GetExpressionsForItemRequest, List<EntityPersistent>> getSynonymsForItemOperation;
  private Operation<GetExpressionsForItemRequest, List<EntityPersistent>> getPhrasesForItemOperation;

  private DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer;
  private DTOTransformer<List<EntityPersistent>, List<MessageDTO>> messageDTOListTransformer;

  @Override
  public MessageDTO addCategory(AddEntityBody body) {

    AddCategoryOperationRequest addCategoryOperationRequest = this.addCategoryRequestTransformer.transform(body);

    EntityPersistent entityPersistent = this.addCategoryOperation.execute(addCategoryOperationRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public MessageDTO addItem(String categoryId, AddEntityBody body) {

    AddItemPathAndBody addItemPathAndBody = new AddItemPathAndBody(categoryId, body);

    AddItemOperationRequest addItemRequest = this.addItemRequestTransformer.transform(addItemPathAndBody);

    EntityPersistent entityPersistent = this.addItemOperation.execute(addItemRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public MessageDTO addSynonym(String categoryId, String itemId, AddEntityBody body) {

    AddSynonymPathAndBody addSynonymPathAndBody = new AddSynonymPathAndBody(categoryId, itemId, body);

    AddSynonymOperationRequest addSynonymOperationRequest = this.addSynonymRequestTransformer.transform(addSynonymPathAndBody);

    EntityPersistent entityPersistent = this.addSynonymOperation.execute(addSynonymOperationRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public MessageDTO addPhrase(String categoryId, String itemId, AddEntityBody body) {

    AddPhraseOperationRequest addPhraseOperationRequest = new AddPhraseOperationRequest();
    addPhraseOperationRequest.setEntity(categoryId);
    addPhraseOperationRequest.setItemId(itemId);
    addPhraseOperationRequest.setDescription(body.getDescription());

    EntityPersistent entityPersistent = this.addPhraseOperation.execute(addPhraseOperationRequest);

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
    GetExpressionsForItemRequest request = new GetExpressionsForItemRequest(categoryId, itemId);

    List<EntityPersistent> entities = this.getSynonymsForItemOperation.execute(request);

    return this.messageDTOListTransformer.transform(entities);
  }

  @Override
  public List<MessageDTO> getPhrasesForItem(String categoryId, String itemId) {
    GetExpressionsForItemRequest request = new GetExpressionsForItemRequest(categoryId, itemId);

    List<EntityPersistent> entities = this.getPhrasesForItemOperation.execute(request);

    return this.messageDTOListTransformer.transform(entities);
  }


  @Resource
  public void setAddCategoryRequestTransformer(RequestTransformer<AddEntityBody, AddCategoryOperationRequest> addCategoryRequestTransformer) {
    this.addCategoryRequestTransformer = addCategoryRequestTransformer;
  }

  @Resource
  public void setAddItemRequestTransformer(RequestTransformer<AddItemPathAndBody, AddItemOperationRequest> addItemRequestTransformer) {
    this.addItemRequestTransformer = addItemRequestTransformer;
  }

  @Resource
  public void setAddSynonymRequestTransformer(RequestTransformer<AddSynonymPathAndBody, AddSynonymOperationRequest> addSynonymRequestTransformer) {
    this.addSynonymRequestTransformer = addSynonymRequestTransformer;
  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }

  @Resource
  public void setAddCategoryOperation(Operation<AddCategoryOperationRequest, EntityPersistent> addCategoryOperation) {
    this.addCategoryOperation = addCategoryOperation;
  }

  @Resource
  public void setAddItemOperation(Operation<AddItemOperationRequest, EntityPersistent> addItemOperation) {
    this.addItemOperation = addItemOperation;
  }

  @Resource
  public void setAddSynonymOperation(Operation<AddSynonymOperationRequest, EntityPersistent> addSynonymOperation) {
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
  public void setGetSynonymsForItemOperation(Operation<GetExpressionsForItemRequest, List<EntityPersistent>> getSynonymsForItemOperation) {
    this.getSynonymsForItemOperation = getSynonymsForItemOperation;
  }

  @Resource
  public void setAddPhraseOperation(Operation<AddPhraseOperationRequest, EntityPersistent> addPhraseOperation) {
    this.addPhraseOperation = addPhraseOperation;
  }

  @Resource
  public void setGetPhrasesForItemOperation(Operation<GetExpressionsForItemRequest, List<EntityPersistent>> getPhrasesForItemOperation) {
    this.getPhrasesForItemOperation = getPhrasesForItemOperation;
  }
}
