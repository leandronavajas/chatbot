package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.RemoveEntitiesFromDBOperationRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;
import java.util.List;

import static unlp.info.chatbot.service.constants.RepositoryConstants.PARENT_ID;

@Component
public class RemoveItemFromDBOperation implements Operation<RemoveEntitiesFromDBOperationRequest, String>  {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  private Operation<RemoveEntitiesFromDBOperationRequest, String> removeExpressionFromDBOperation;

  @Override
  public String execute(RemoveEntitiesFromDBOperationRequest request) {
    EntityPersistent entityPersistent = request.getEntityPersistent();

    List<EntityPersistent> expressionsToRemove = this.messageRepositoryService.getAllSafe(PARENT_ID, entityPersistent.getId());

    if (!CollectionUtils.isEmpty(expressionsToRemove)) {
      for (EntityPersistent expression : expressionsToRemove) {
        RemoveEntitiesFromDBOperationRequest itemRemoveRequest = new RemoveEntitiesFromDBOperationRequest(expression);
        this.removeExpressionFromDBOperation.execute(itemRemoveRequest);
      }
    }

    this.messageRepositoryService.remove(entityPersistent);

    return "OK";
  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }

  @Resource
  public void setRemoveExpressionFromDBOperation(Operation<RemoveEntitiesFromDBOperationRequest, String> removeExpressionFromDBOperation) {
    this.removeExpressionFromDBOperation = removeExpressionFromDBOperation;
  }
}
