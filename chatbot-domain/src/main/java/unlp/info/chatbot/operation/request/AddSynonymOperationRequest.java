package unlp.info.chatbot.operation.request;

public class AddSynonymOperationRequest extends AddItemOperationRequest {

  private String synonymId;

  public String getSynonymId() {
    return synonymId;
  }

  public void setSynonymId(String synonymId) {
    this.synonymId = synonymId;
  }
}
