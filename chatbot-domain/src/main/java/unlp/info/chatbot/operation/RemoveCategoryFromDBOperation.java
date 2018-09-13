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
public class RemoveCategoryFromDBOperation implements Operation<RemoveEntitiesFromDBOperationRequest, String> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  private Operation<RemoveEntitiesFromDBOperationRequest, String> removeItemFromDBOperation;

  @Override
  public String execute(RemoveEntitiesFromDBOperationRequest request) {
    EntityPersistent entityPersistent = request.getEntityPersistent();

    List<EntityPersistent> itemsToRemove = this.messageRepositoryService.getAllSafe(PARENT_ID, entityPersistent.getId());

    if (!CollectionUtils.isEmpty(itemsToRemove)) {
      for (EntityPersistent item : itemsToRemove) {
        RemoveEntitiesFromDBOperationRequest itemRemoveRequest = new RemoveEntitiesFromDBOperationRequest(item);
        this.removeItemFromDBOperation.execute(itemRemoveRequest);
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
  public void setRemoveItemFromDBOperation(Operation<RemoveEntitiesFromDBOperationRequest, String> removeItemFromDBOperation) {
    this.removeItemFromDBOperation = removeItemFromDBOperation;
  }
}
