package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.ResponseDTO;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.exception.NullEntityException;
import unlp.info.chatbot.operation.request.RemoveResponseRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;

@Component
public class RemoveResponseOperation implements Operation<RemoveResponseRequest, StatusResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveResponseOperation.class);

  private RepositoryService<ResponseDTO> repositoryService;

  @Override
  public StatusResponse execute(RemoveResponseRequest request) {
    String entity = request.getEntity();

    if (null == entity) {
      LOGGER.error("[REMOVE RESPONSE OPERATION] Entity cannot be null");
      throw new NullEntityException("Entity cannot be null");
    }

    this.repositoryService.remove(entity);

    return new StatusResponse("SUCCESS", String.format("Item with id: %s has been removed", entity));
  }

  @Resource
  public void setRepositoryService(RepositoryService<ResponseDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
