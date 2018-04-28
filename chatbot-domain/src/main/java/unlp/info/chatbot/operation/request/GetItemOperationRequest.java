package unlp.info.chatbot.operation.request;

public class GetItemOperationRequest {

  private String id;


  public GetItemOperationRequest(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
