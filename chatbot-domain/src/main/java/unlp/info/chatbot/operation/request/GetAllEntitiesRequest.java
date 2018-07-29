package unlp.info.chatbot.operation.request;

public class GetAllEntitiesRequest {

  private String filter;

  public GetAllEntitiesRequest(String filter) {
    this.filter = filter;
  }

  public String getFilter() {
    return filter;
  }
}
