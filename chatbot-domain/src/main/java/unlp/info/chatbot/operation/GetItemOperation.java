package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.TestDTO;
import unlp.info.chatbot.operation.request.GetItemOperationRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;

@Component
public class GetItemOperation implements Operation<GetItemOperationRequest, TestDTO> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetItemOperation.class);

  private RepositoryService<TestDTO> repositoryService;

  public TestDTO execute(GetItemOperationRequest request) {

    String id = request.getId();
    TestDTO response = this.repositoryService.getById(id);

    LOGGER.info("[GET ITEM OPERATION] Response: {}", response);

    return response;
  }

  @Resource
  public void setRepositoryService(RepositoryService<TestDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
