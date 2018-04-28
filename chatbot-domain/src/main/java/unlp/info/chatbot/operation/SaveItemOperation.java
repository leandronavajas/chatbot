package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.TestDTO;
import unlp.info.chatbot.operation.request.SaveItemOperationRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;

@Component
public class SaveItemOperation implements Operation<SaveItemOperationRequest, TestDTO> {

  private static final Logger LOGGER = LoggerFactory.getLogger(SaveItemOperation.class);

  private RepositoryService repositoryService;

  @Override
  public TestDTO execute(SaveItemOperationRequest request) {

    TestDTO item = new TestDTO(request.getId(), request.getDescription());
    LOGGER.info("[SAVE ITEM OPERATION] Item created, trying save ..");

    this.repositoryService.save(item);

    LOGGER.info("[SAVE ITEM OPERATION] Item saved: {}", item);
    return item;
  }

  @Resource
  public void setRepositoryService(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }
}
