package unlp.info.chatbot.client.request;

public class AddSynonymWitRequest extends AddItemWitRequest {

  private String synonymId;

  public String getSynonymId() {
    return synonymId;
  }

  public void setSynonymId(String synonymId) {
    this.synonymId = synonymId;
  }

}
