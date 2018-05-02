package unlp.info.chatbot.operation.request;

public class RemoveResponseRequest {

  private String entity;

  public RemoveResponseRequest(String entity) {
    this.entity = entity;
  }

  public String getEntity() {
    return entity;
  }
}
