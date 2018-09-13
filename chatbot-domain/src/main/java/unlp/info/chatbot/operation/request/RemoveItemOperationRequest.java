package unlp.info.chatbot.operation.request;

public class RemoveItemOperationRequest implements RemoveEntityRequest {

  private String categoryId;
  private String itemId;

  public RemoveItemOperationRequest(String categoryId, String itemId) {
    this.categoryId = categoryId;
    this.itemId= itemId;
  }

  public String getItemId() {
    return itemId;
  }

  public String getCategoryId() {
    return categoryId;
  }

  @Override
  public String getEntityId() {
    return itemId;
  }
}
