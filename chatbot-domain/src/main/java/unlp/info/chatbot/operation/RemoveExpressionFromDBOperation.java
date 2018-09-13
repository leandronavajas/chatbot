package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.RemoveEntitiesFromDBOperationRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;

@Component
public class RemoveExpressionFromDBOperation  implements Operation<RemoveEntitiesFromDBOperationRequest, String>  {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  @Override
  public String execute(RemoveEntitiesFromDBOperationRequest request) {
    EntityPersistent entityPersistent = request.getEntityPersistent();

    this.messageRepositoryService.remove(entityPersistent);

    return "OK";
  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }
}
