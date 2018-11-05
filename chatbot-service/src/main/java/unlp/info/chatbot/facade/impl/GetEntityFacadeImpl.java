package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.Entity;
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

  private Operation<GetAllEntitiesRequest, List<Entity>> getAllEntitiesOperation;
  private Operation<GetItemsForCategoryRequest, List<Entity>> getItemsForCategoryOperation;
  private Operation<GetExpressionsForItemRequest, List<Entity>> getSynonymsForItemOperation;
  private Operation<GetExpressionsForItemRequest, List<Entity>> getPhrasesForItemOperation;


  private DTOTransformer<List<Entity>, List<MessageDTO>> messageDTOListTransformer;

  @Override
  public List<MessageDTO> getAll(String filter) {

    GetAllEntitiesRequest request = new GetAllEntitiesRequest(filter);

    List<Entity> entitiesPersistent = this.getAllEntitiesOperation.execute(request);

    return this.messageDTOListTransformer.transform(entitiesPersistent);
  }

  @Override
  public List<MessageDTO> getItemsForCategory(String categoryId) {
    GetItemsForCategoryRequest request = new GetItemsForCategoryRequest(categoryId);

    List<Entity> entities = this.getItemsForCategoryOperation.execute(request);

    return this.messageDTOListTransformer.transform(entities);
  }

  @Override
  public List<MessageDTO> getSynonymsForItem(String categoryId, String itemId) {
    GetExpressionsForItemRequest request = new GetExpressionsForItemRequest(categoryId, itemId);

    List<Entity> entities = this.getSynonymsForItemOperation.execute(request);

    return this.messageDTOListTransformer.transform(entities);
  }

  @Override
  public List<MessageDTO> getPhrasesForItem(String categoryId, String itemId) {
    GetExpressionsForItemRequest request = new GetExpressionsForItemRequest(categoryId, itemId);

    List<Entity> entities = this.getPhrasesForItemOperation.execute(request);

    return this.messageDTOListTransformer.transform(entities);
  }


  @Resource
  public void setGetAllEntitiesOperation(Operation<GetAllEntitiesRequest, List<Entity>> getAllEntitiesOperation) {
    this.getAllEntitiesOperation = getAllEntitiesOperation;
  }

  @Resource
  public void setMessageDTOListTransformer(DTOTransformer<List<Entity>, List<MessageDTO>> messageDTOListTransformer) {
    this.messageDTOListTransformer = messageDTOListTransformer;
  }

  @Resource
  public void setGetItemsForCategoryOperation(Operation<GetItemsForCategoryRequest, List<Entity>> getItemsForCategoryOperation) {
    this.getItemsForCategoryOperation = getItemsForCategoryOperation;
  }

  @Resource
  public void setGetSynonymsForItemOperation(Operation<GetExpressionsForItemRequest, List<Entity>> getSynonymsForItemOperation) {
    this.getSynonymsForItemOperation = getSynonymsForItemOperation;
  }

  @Resource
  public void setGetPhrasesForItemOperation(Operation<GetExpressionsForItemRequest, List<Entity>> getPhrasesForItemOperation) {
    this.getPhrasesForItemOperation = getPhrasesForItemOperation;
  }

}
