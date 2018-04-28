package unlp.info.chatbot.exception;

public class ApiException extends RuntimeException {

  private Integer code;
  private String status;
  private String causes;

  public ApiException(Integer code, String status, String causes) {
    this.code = code;
    this.status = status;
    this.causes = causes;
  }

  public Integer getCode() {
    return code;
  }

  public String getCauses() {
    return causes;
  }

  public String getStatus() {
    return status;
  }
}
