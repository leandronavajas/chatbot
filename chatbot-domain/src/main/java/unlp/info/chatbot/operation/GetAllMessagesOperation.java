package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.MessagePersistent;
import unlp.info.chatbot.operation.request.GetAllMessageOperationRequest;
import unlp.info.chatbot.service.RepositoryServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetAllMessagesOperation implements Operation<GetAllMessageOperationRequest, List<MessagePersistent>> {

  private RepositoryServiceImpl<MessagePersistent> repositoryService;

  @Override
  public List<MessagePersistent> execute(GetAllMessageOperationRequest request) {
    return this.repositoryService.getAll();
  }

  @Resource
  public void setRepositoryService(RepositoryServiceImpl<MessagePersistent> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
