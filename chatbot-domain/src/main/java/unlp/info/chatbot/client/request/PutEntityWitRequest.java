package unlp.info.chatbot.client.request;

import java.util.List;

public class PutEntityWitRequest {

  private String entityName;
  private List<String> lookups;


  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  public List<String> getLookups() {
    return lookups;
  }

  public void setLookups(List<String> lookups) {
    this.lookups = lookups;
  }
}
