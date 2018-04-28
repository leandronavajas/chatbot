package unlp.info.chatbot.dto;

public class StatusResponse {

  private String status;
  private String description;

  public StatusResponse(String status, String description) {
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
