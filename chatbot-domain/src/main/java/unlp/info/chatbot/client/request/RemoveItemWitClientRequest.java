package unlp.info.chatbot.client.request;

public class RemoveItemWitClientRequest {

  private String categoryId;
  private String itemId;

  public RemoveItemWitClientRequest(String categoryId, String itemId) {
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
