package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.operation.request.*;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.facade.GetEntityFacade;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.Operation;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetEntityFacadeImpl implements GetEntityFacade {

  private Operation<GetAllEntitiesRequest, List<EntityPersistent>> getAllEntitiesOperation;
  private Operation<GetItemsForCategoryRequest, List<EntityPersistent>> getItemsForCategoryOperation;
  private Operation<GetExpressionsForItemRequest, List<EntityPersistent>> getSynonymsForItemOperation;
  private Operation<GetExpressionsForItemRequest, List<EntityPersistent>> getPhrasesForItemOperation;


  private DTOTransformer<List<EntityPersistent>, List<MessageDTO>> messageDTOListTransformer;

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
  public void setGetPhrasesForItemOperation(Operation<GetExpressionsForItemRequest, List<EntityPersistent>> getPhrasesForItemOperation) {
    this.getPhrasesForItemOperation = getPhrasesForItemOperation;
  }
}
