package unlp.info.chatbot.operation.request;

//import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;

public class SaveItemOperationRequest {

  private String id;
  private String description;

  public SaveItemOperationRequest(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "Te debo el toString";
    //return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
