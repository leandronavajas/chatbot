package unlp.info.chatbot.operation.request;

public class AddExpressionRequest extends AddItemRequest {

  private String expressionId;

  public String getExpressionId() {
    return expressionId;
  }

  public void setExpressionId(String expressionId) {
    this.expressionId = expressionId;
  }
}
