package unlp.info.chatbot.operation.request;

public class GetItemsForCategoryRequest {

  private String categoryId;

  public GetItemsForCategoryRequest(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryId() {
    return categoryId;
  }
}
