package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.Entity;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetAllEntitiesRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.service.constants.RepositoryConstants;
import unlp.info.chatbot.transformer.ModelTransformer;

import javax.annotation.Resource;
import java.util.List;

import static unlp.info.chatbot.service.constants.RepositoryConstants.KIND;

@Component
public class GetAllEntitiesOperation implements Operation<GetAllEntitiesRequest, List<Entity>> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  private ModelTransformer<List<EntityPersistent>, List<Entity>> entityListTransformer;

  @Override
  public List<Entity> execute(GetAllEntitiesRequest request) {

    List<EntityPersistent> entitiesPersistent = this.messageRepositoryService.getAll(KIND, request.getFilter());
    return this.entityListTransformer.transform(entitiesPersistent);

  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }

  @Resource
  public void setEntityListTransformer(ModelTransformer<List<EntityPersistent>, List<Entity>> entityListTransformer) {
    this.entityListTransformer = entityListTransformer;
  }
}
