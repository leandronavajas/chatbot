package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.Entity;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetItemsForCategoryRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.transformer.ModelTransformer;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetItemsForCategoryOperation implements Operation<GetItemsForCategoryRequest, List<Entity>> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  private ModelTransformer<List<EntityPersistent>, List<Entity>> entityListTransformer;

  @Override
  public List<Entity> execute(GetItemsForCategoryRequest request) {

    List<EntityPersistent> entitiesPersistent = this.messageRepositoryService.getItems(request.getCategoryId());

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
