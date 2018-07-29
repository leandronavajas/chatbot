package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetAllEntitiesRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetAllEntitiesOperation implements Operation<GetAllEntitiesRequest, List<EntityPersistent>> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  @Override
  public List<EntityPersistent> execute(GetAllEntitiesRequest request) {

    return this.messageRepositoryService.getAll(request.getFilter());

  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }
}
