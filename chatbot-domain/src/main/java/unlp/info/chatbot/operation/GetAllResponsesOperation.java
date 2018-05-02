package unlp.info.chatbot.operation;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.ResponseDTO;
import unlp.info.chatbot.operation.request.GetAllItemsOperationRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;
import java.util.List;

@Component
public class GetAllResponsesOperation implements Operation<GetAllItemsOperationRequest, List<ResponseDTO>> {

  private RepositoryService<ResponseDTO> repositoryService;

  @Override
  public List<ResponseDTO> execute(GetAllItemsOperationRequest request) {
    return this.repositoryService.getAll();
  }

  @Resource
  public void setRepositoryService(RepositoryService<ResponseDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
