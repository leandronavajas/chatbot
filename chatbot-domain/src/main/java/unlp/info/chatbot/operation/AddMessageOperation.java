package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.exception.NullEntityException;
import unlp.info.chatbot.operation.request.AddMessageRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.transformer.Transformer;

import javax.annotation.Resource;

@Component
public class AddMessageOperation implements Operation<AddMessageRequest, MessageDTO> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddMessageOperation.class);

  private RepositoryService<MessageDTO> repositoryService;

  private RepositoryService<MessageDTO> repositoryServiceIndex;

  private Transformer<AddMessageRequest, MessageDTO> messageDTOTransformer;

  @Override
  public MessageDTO execute(AddMessageRequest request) {

    if (request.getEntity() == null) {
      LOGGER.error("[ADD MESSAGE OPERATION] Entity cannot be null");
      throw new NullEntityException("Entity cannot be null");
    }

    MessageDTO messageDTO = this.messageDTOTransformer.transform(request);

    // TODO: LN aca debemos agregar el objeto al index map
    //LOGGER.info("[ADD MESSAGE OPERATION] Add message to index map");
    //this.repositoryServiceIndex.save(messageDTO);

    LOGGER.info("[ADD MESSAGE OPERATION] Add message to model");

    if (request.getParentId() != null) {
      MessageDTO parent = this.repositoryService.getById(request.getParentId());

      if (parent == null) {
        LOGGER.warn("[ADD MESSAGE OPERATION] Parent_ID: {} not found. Add message to base", request.getParentId());
        this.repositoryService.save(messageDTO);
        return messageDTO;
      }

      LOGGER.debug("[ADD MESSAGE OPERATION] Add Entity to parent");
      parent.getSiblings().add(messageDTO);

      return messageDTO;
    }

    this.repositoryService.save(messageDTO);
    return messageDTO;
  }

  @Resource
  public void setRepositoryService(RepositoryService<MessageDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }

  @Resource
  public void setMessageDTOTransformer(Transformer<AddMessageRequest, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }

  @Resource
  public void setRepositoryServiceIndex(RepositoryService<MessageDTO> repositoryServiceIndex) {
    this.repositoryServiceIndex = repositoryServiceIndex;
  }
}
