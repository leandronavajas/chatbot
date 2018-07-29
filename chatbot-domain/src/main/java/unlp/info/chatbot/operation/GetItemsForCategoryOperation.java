package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.GetItemsForCategoryRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetItemsForCategoryOperation implements Operation<GetItemsForCategoryRequest, List<EntityPersistent>> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  @Override
  public List<EntityPersistent> execute(GetItemsForCategoryRequest request) {

    return this.messageRepositoryService.getItems(request.getCategoryId());

  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }
}
