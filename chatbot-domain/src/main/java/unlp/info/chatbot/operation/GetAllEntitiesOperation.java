package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetAllEntitiesRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.service.constants.RepositoryConstants;

import javax.annotation.Resource;
import java.util.List;

import static unlp.info.chatbot.service.constants.RepositoryConstants.KIND;

@Component
public class GetAllEntitiesOperation implements Operation<GetAllEntitiesRequest, List<EntityPersistent>> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  @Override
  public List<EntityPersistent> execute(GetAllEntitiesRequest request) {

    return this.messageRepositoryService.getAll(KIND, request.getFilter());

  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }
}
