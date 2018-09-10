package unlp.info.chatbot.client.request;

public class RemoveCategoryWitClientRequest {

  private String categoryId;

  public RemoveCategoryWitClientRequest(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryId() {
    return categoryId;
  }

}
