package unlp.info.chatbot.operation.request;

public class GetExpressionsForItemRequest {

  private String categoryId;
  private String itemId;

  public GetExpressionsForItemRequest(String categoryId, String itemId) {
    this.categoryId = categoryId;
    this.itemId = itemId;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public String getItemId() {
    return itemId;
  }
}
