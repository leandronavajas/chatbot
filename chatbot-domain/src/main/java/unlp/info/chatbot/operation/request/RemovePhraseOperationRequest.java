package unlp.info.chatbot.operation.request;

public class RemovePhraseOperationRequest implements RemoveEntityRequest{

  private String categoryId;
  private String itemId;
  private String phrase;

  public RemovePhraseOperationRequest(String categoryId, String itemId, String phrase) {
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

  @Override
  public String getDBId() {
    return phrase;
  }
}
