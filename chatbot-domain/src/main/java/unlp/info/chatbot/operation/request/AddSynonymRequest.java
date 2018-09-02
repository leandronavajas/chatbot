package unlp.info.chatbot.operation.request;

public class AddSynonymRequest extends AddItemRequest {

  private String synonymId;

  public String getSynonymId() {
    return synonymId;
  }

  public void setSynonymId(String synonymId) {
    this.synonymId = synonymId;
  }
}
