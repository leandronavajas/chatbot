package unlp.info.chatbot.operation.request;

public class AddItemRequest extends AddCategoryRequest {

  private String itemId;

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }
}
