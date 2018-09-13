package unlp.info.chatbot.operation.request;

public class RemoveSynonymOperationRequest implements RemoveEntityRequest{

  private String categoryId;
  private String itemId;
  private String synonymId;

  public RemoveSynonymOperationRequest(String categoryId, String itemId, String synonymId) {
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

  @Override
  public String getEntityId() {
    return synonymId;
  }
}
