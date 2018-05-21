package unlp.info.chatbot.model;

public class RemoveStatus {

  private String status;
  private String description;

  public RemoveStatus(String status, String description) {
    this.status = status;
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public String getDescription() {
    return description;
  }
}
