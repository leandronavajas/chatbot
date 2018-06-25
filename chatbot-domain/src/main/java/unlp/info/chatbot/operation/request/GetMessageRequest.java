package unlp.info.chatbot.operation.request;

public class GetMessageRequest {

  private String phrase;

  public GetMessageRequest(String phrase) {
    this.phrase = phrase;
  }

  public String getPhrase() {
    return phrase;
  }

}
