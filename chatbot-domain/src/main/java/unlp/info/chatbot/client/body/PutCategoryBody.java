package unlp.info.chatbot.client.body;

import java.util.List;

public class PutCategoryBody extends AbstractWitBody {

  private List<String> lookups;

  public List<String> getLookups() {
    return lookups;
  }

  public void setLookups(List<String> lookups) {
    this.lookups = lookups;
  }

}
