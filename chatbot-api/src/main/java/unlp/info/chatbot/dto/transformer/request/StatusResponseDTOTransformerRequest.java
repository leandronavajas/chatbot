package unlp.info.chatbot.dto.transformer.request;

public class StatusResponseDTOTransformerRequest {

  private String status;
  private String entityId;

  public StatusResponseDTOTransformerRequest(String status, String entityId) {
    this.status = status;
    this.entityId = entityId;
  }

  public String getStatus() {
    return status;
  }

  public String getEntityId() {
    return entityId;
  }
}
