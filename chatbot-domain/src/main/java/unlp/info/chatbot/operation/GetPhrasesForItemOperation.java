package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.Entity;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetExpressionsForItemRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.transformer.ModelTransformer;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetPhrasesForItemOperation implements Operation<GetExpressionsForItemRequest, List<Entity>> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  private ModelTransformer<List<EntityPersistent>, List<Entity>> entityListTransformer;

  @Override
  public List<Entity> execute(GetExpressionsForItemRequest request) {
    List<EntityPersistent> phrases = this.messageRepositoryService.getPhrases(request.getCategoryId(), request.getItemId());
    return this.entityListTransformer.transform(phrases);
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
