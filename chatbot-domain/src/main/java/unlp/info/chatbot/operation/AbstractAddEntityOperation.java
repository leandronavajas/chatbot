package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.exception.NullEntityException;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddCategoryOperationRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.transformer.PersistentTransformer;

import javax.annotation.Resource;

public abstract class AbstractAddEntityOperation<R extends AddCategoryOperationRequest, P extends EntityPersistent> implements Operation<R, P>{

  RepositoryService<EntityPersistent> messageRepositoryService;

  @Override
  public P execute(R request) {

    if (request.getEntity() == null) {
      this.getLogger().error("[ADD MESSAGE OPERATION] Entity cannot be null");
      throw new NullEntityException("Entity cannot be null");
    }

    AddEntityWitResponse witResponse = this.callWit(request);
    this.getLogger().info("[ADD MESSAGE OPERATION] Wit response: {}", witResponse);

    this.fillRequestWithWitResponse(request, witResponse);

    this.getLogger().info("[ADD MESSAGE OPERATION] Add message to model");
    P messagePersistent = this.getPersistentTransformer().transform(request);

    this.messageRepositoryService.save(messagePersistent);
    return messagePersistent;

  }

  protected void fillRequestWithWitResponse(R request, AddEntityWitResponse witResponse) {
    request.setWitId(witResponse.getId());
  }

  protected abstract Logger getLogger();

  protected abstract AddEntityWitResponse callWit(R request);

  protected abstract PersistentTransformer<R, P> getPersistentTransformer();

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }
}
