package unlp.info.chatbot.operation.request;

public class RemoveItemOperationRequest {

  private String id;


  public RemoveItemOperationRequest(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
