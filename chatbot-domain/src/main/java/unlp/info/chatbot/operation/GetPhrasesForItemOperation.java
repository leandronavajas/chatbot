package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetExpressionsForItemRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetPhrasesForItemOperation implements Operation<GetExpressionsForItemRequest, List<EntityPersistent>> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  @Override
  public List<EntityPersistent> execute(GetExpressionsForItemRequest request) {
    return this.messageRepositoryService.getPhrases(request.getCategoryId(), request.getItemId());
  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }
}
