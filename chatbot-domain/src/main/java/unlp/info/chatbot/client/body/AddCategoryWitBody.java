package unlp.info.chatbot.client.body;

public class AddCategoryWitBody extends AbstractWitBody {

  private String id;
  private String doc;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDoc() {
    return doc;
  }

  public void setDoc(String doc) {
    this.doc = doc;
  }

}
