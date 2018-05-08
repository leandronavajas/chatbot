package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.exception.NullEntityException;
import unlp.info.chatbot.operation.request.RemoveMessageRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;

@Component
public class RemoveMessageOperation implements Operation<RemoveMessageRequest, StatusResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveMessageOperation.class);

  private RepositoryService<MessageDTO> repositoryService;

  @Override
  public StatusResponse execute(RemoveMessageRequest request) {
    String entity = request.getEntity();

    if (null == entity) {
      LOGGER.error("[REMOVE MESSAGE OPERATION] Entity cannot be null");
      throw new NullEntityException("Entity cannot be null");
    }

    this.repositoryService.remove(entity);

    return new StatusResponse("SUCCESS", String.format("Item with id: %s has been removed", entity));
  }

  @Resource
  public void setRepositoryService(RepositoryService<MessageDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
