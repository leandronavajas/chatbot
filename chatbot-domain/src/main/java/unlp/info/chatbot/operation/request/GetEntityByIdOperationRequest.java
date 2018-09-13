package unlp.info.chatbot.operation.request;

public class GetEntityByIdOperationRequest {

  private String entityId;

  public GetEntityByIdOperationRequest(String entityId) {
    this.entityId = entityId;
  }

  public String getEntityId() {
    return entityId;
  }
}
