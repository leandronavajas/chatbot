package unlp.info.chatbot.operation.request;

public class AddItemOperationRequest extends AddCategoryOperationRequest {

  private String itemId;

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }
}
