package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetSynonymsForItemRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetSynonymsForItemOperation implements Operation<GetSynonymsForItemRequest, List<EntityPersistent>> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  @Override
  public List<EntityPersistent> execute(GetSynonymsForItemRequest request) {
    return this.messageRepositoryService.getSynonyms(request.getCategoryId(), request.getItemId());
  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }
}
