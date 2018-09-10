package unlp.info.chatbot.client.request;

public class RemoveSynonymWitClientRequest {

  private String categoryId;
  private String itemId;
  private String synonymId;

  public RemoveSynonymWitClientRequest(String categoryId, String itemId, String synonymId) {
    this.categoryId = categoryId;
    this.itemId = itemId;
    this.synonymId = synonymId;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public String getItemId() {
    return itemId;
  }

  public String getSynonymId() {
    return synonymId;
  }
}
