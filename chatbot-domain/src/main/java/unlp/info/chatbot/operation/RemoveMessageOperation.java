package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.exception.NullEntityException;
import unlp.info.chatbot.model.MessagePersistent;
import unlp.info.chatbot.model.RemoveStatus;
import unlp.info.chatbot.operation.request.RemoveMessageRequest;
import unlp.info.chatbot.service.RepositoryServiceImpl;

import javax.annotation.Resource;

@Component
public class RemoveMessageOperation implements Operation<RemoveMessageRequest, RemoveStatus> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveMessageOperation.class);

  private RepositoryServiceImpl<MessagePersistent> repositoryService;

  @Override
  public RemoveStatus execute(RemoveMessageRequest request) {
    String entity = request.getEntity();

    if (null == entity) {
      LOGGER.error("[REMOVE MESSAGE OPERATION] Entity cannot be null");
      throw new NullEntityException("Entity cannot be null");
    }

    this.repositoryService.remove(entity);

    return new RemoveStatus("SUCCESS", String.format("Item with id: %s has been removed", entity));
  }

  @Resource
  public void setRepositoryService(RepositoryServiceImpl<MessagePersistent> repositoryService) {
    this.repositoryService = repositoryService;
  }
}
