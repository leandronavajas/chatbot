package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetAllMessageOperationRequest;
import unlp.info.chatbot.service.RepositoryServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetAllMessagesOperation implements Operation<GetAllMessageOperationRequest, List<EntityPersistent>> {

  private RepositoryServiceImpl<EntityPersistent> repositoryService;

  @Override
  public List<EntityPersistent> execute(GetAllMessageOperationRequest request) {
    return this.repositoryService.getAll(null);
  }

  @Resource
  public void setRepositoryService(RepositoryServiceImpl<EntityPersistent> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
