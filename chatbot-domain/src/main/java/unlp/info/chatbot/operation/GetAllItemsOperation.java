package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.TestDTO;
import unlp.info.chatbot.operation.request.GetAllItemsOperationRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetAllItemsOperation implements Operation<GetAllItemsOperationRequest, List<TestDTO>> {

  private RepositoryService<TestDTO> repositoryService;

  @Override
  public List<TestDTO> execute(GetAllItemsOperationRequest request) {
    return this.repositoryService.getAll();
  }

  @Resource
  public void setRepositoryService(RepositoryService<TestDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
