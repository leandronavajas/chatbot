package unlp.info.chatbot.client.request;

public class RemovePhraseWitClientRequest {

  private String categoryId;
  private String itemId;
  private String phrase;

  public RemovePhraseWitClientRequest(String categoryId, String itemId, String phrase) {
    this.categoryId = categoryId;
    this.itemId = itemId;
    this.phrase = phrase;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public String getItemId() {
    return itemId;
  }

  public String getPhrase() {
    return phrase;
  }
}
