package unlp.info.chatbot.operation.request;

public class RemoveCategoryOperationRequest implements RemoveEntityRequest{

  private String categoryId;

  public RemoveCategoryOperationRequest(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryId() {
    return categoryId;
  }

  @Override
  public String getDBId() {
    return categoryId;
  }
}
