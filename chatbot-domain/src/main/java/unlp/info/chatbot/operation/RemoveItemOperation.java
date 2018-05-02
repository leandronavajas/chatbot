package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.dto.TestDTO;
import unlp.info.chatbot.operation.request.RemoveItemOperationRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;

@Component
public class RemoveItemOperation implements Operation<RemoveItemOperationRequest, StatusResponse> {

  private RepositoryService<TestDTO> repositoryService;

  @Override
  public StatusResponse execute(RemoveItemOperationRequest request) {

    this.repositoryService.remove(request.getId());
    return new StatusResponse("SUCCESS", String.format("Item with id: %s has been removed", request.getId()));
  }

  @Resource
  public void setRepositoryService(RepositoryService<TestDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
