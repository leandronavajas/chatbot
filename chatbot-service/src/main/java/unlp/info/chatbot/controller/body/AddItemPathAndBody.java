package unlp.info.chatbot.controller.body;

public class AddItemPathAndBody {

  private String categoryId;
  private AddEntityBody body;

  public AddItemPathAndBody(String categoryId, AddEntityBody body) {
    this.categoryId = categoryId;
    this.body = body;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public AddEntityBody getBody() {
    return body;
  }

}
