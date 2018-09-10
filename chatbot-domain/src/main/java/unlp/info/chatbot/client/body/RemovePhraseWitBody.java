package unlp.info.chatbot.client.body;

public class RemovePhraseWitBody extends AbstractWitBody {

  private String text;

  public RemovePhraseWitBody(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
