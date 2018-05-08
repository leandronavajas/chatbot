package unlp.info.chatbot.operation.request;

public class RemoveMessageRequest {

  private String entity;

  public RemoveMessageRequest(String entity) {
    this.entity = entity;
  }

  public String getEntity() {
    return entity;
  }
}
