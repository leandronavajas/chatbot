package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetEntityByIdOperationRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;

@Component
public class GetEntityByIdOperation implements Operation<GetEntityByIdOperationRequest, EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetEntityByIdOperation.class);

  private RepositoryService<EntityPersistent> messageRepositoryService;

  @Override
  public EntityPersistent execute(GetEntityByIdOperationRequest request) {
    LOGGER.info("Get entity by id: {}", request.getEntityId());
    return this.messageRepositoryService.getById(request.getEntityId());
  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }
}
