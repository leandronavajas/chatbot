package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.exception.NullEntityException;
import unlp.info.chatbot.model.MessagePersistent;
import unlp.info.chatbot.operation.request.AddMessageRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.transformer.PersistentTransformer;

import javax.annotation.Resource;

@Component
public class AddMessageOperation implements Operation<AddMessageRequest, MessagePersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddMessageOperation.class);

  private RepositoryService<MessagePersistent> messageRepositoryService;

  private PersistentTransformer<AddMessageRequest, MessagePersistent> messagePersistentTransformer;

  @Override
  public MessagePersistent execute(AddMessageRequest request) {

    if (request.getEntity() == null) {
      LOGGER.error("[ADD MESSAGE OPERATION] Entity cannot be null");
      throw new NullEntityException("Entity cannot be null");
    }

    MessagePersistent messagePersistent = this.messagePersistentTransformer.transform(request);

    LOGGER.info("[ADD MESSAGE OPERATION] Add message to model");

    if (request.getParentId() != null) {
      MessagePersistent parent = this.messageRepositoryService.getById(request.getParentId());

      if (parent == null) {
        LOGGER.warn("[ADD MESSAGE OPERATION] Parent_ID: {} not found. Add message to base", request.getParentId());
        this.messageRepositoryService.save(messagePersistent);
        return messagePersistent;
      }

      LOGGER.debug("[ADD MESSAGE OPERATION] Add Entity to parent");
      parent.getSiblings().add(messagePersistent.getId());

      return messagePersistent;
    }

    this.messageRepositoryService.save(messagePersistent);
    return messagePersistent;
  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<MessagePersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }

  @Resource
  public void setMessagePersistentTransformer(PersistentTransformer<AddMessageRequest, MessagePersistent> messagePersistentTransformer) {
    this.messagePersistentTransformer = messagePersistentTransformer;
  }
}
