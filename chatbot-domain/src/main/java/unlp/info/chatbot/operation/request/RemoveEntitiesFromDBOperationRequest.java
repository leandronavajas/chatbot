package unlp.info.chatbot.operation.request;

import unlp.info.chatbot.model.EntityPersistent;

public class RemoveEntitiesFromDBOperationRequest {
  private EntityPersistent entityPersistent;

  public RemoveEntitiesFromDBOperationRequest(EntityPersistent entityPersistent) {
    this.entityPersistent = entityPersistent;
  }

  public EntityPersistent getEntityPersistent() {
    return entityPersistent;
  }

}
