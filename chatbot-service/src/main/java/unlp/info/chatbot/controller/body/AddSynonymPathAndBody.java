package unlp.info.chatbot.controller.body;

public class AddSynonymPathAndBody {

  private String categoryId;
  private String itemId;
  private AddEntityBody body;

  public AddSynonymPathAndBody(String categoryId, String itemId, AddEntityBody body) {
    this.categoryId = categoryId;
    this.itemId = itemId;
    this.body = body;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public String getItemId() {
    return itemId;
  }

  public AddEntityBody getBody() {
    return body;
  }
}
