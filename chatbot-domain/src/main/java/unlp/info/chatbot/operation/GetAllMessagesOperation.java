package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.operation.request.GetAllMessageOperationRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetAllMessagesOperation implements Operation<GetAllMessageOperationRequest, List<MessageDTO>> {

  private RepositoryService<MessageDTO> repositoryService;

  @Override
  public List<MessageDTO> execute(GetAllMessageOperationRequest request) {
    return this.repositoryService.getAll();
  }

  @Resource
  public void setRepositoryService(RepositoryService<MessageDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
