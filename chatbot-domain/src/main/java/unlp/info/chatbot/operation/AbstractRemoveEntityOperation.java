package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.RemoveEntitiesFromDBOperationRequest;
import unlp.info.chatbot.operation.request.RemoveEntityRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;

@Component
public abstract class AbstractRemoveEntityOperation<R extends RemoveEntityRequest> implements Operation<R, String> {

  private RepositoryService<EntityPersistent> messageRepositoryService;
  private Operation<RemoveEntitiesFromDBOperationRequest, String> removeEntityFromDBWrapper;

  @Override
  public String execute(R request) {

    this.callWit(request);

    this.removeFromDB(request);

    return "OK";
  }

  protected abstract void callWit(R request);

  private void removeFromDB(R request) {
    EntityPersistent entitiesToRemove = this.messageRepositoryService.getById(request.getEntityId());

    RemoveEntitiesFromDBOperationRequest removeRequest = new RemoveEntitiesFromDBOperationRequest(entitiesToRemove);
    this.removeEntityFromDBWrapper.execute(removeRequest);
  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }

  @Resource(name = "removeEntityFromDBWrapper")
  public void setRemoveEntityFromDBWrapper(Operation<RemoveEntitiesFromDBOperationRequest, String> removeEntityFromDBWrapper) {
    this.removeEntityFromDBWrapper = removeEntityFromDBWrapper;
  }
}
