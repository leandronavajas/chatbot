package unlp.info.chatbot.operation;

import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.RemoveEntityRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;

public abstract class AbstractRemoveEntityOperation<R extends RemoveEntityRequest> implements Operation<R, String> {

  private RepositoryService<EntityPersistent> messageRepositoryService;

  @Override
  public String execute(R request) {

    this.callWit(request);

    this.removeFromDB(request);

    return "OK";
  }

  protected abstract void callWit(R request);

  private void removeFromDB(R request) {
    this.messageRepositoryService.remove(request.getDBId());
  }

  @Resource
  public void setMessageRepositoryService(RepositoryService<EntityPersistent> messageRepositoryService) {
    this.messageRepositoryService = messageRepositoryService;
  }
}
