package unlp.info.chatbot.client.body;

import java.util.List;

public class AddItemWitBody extends AbstractWitBody {

  private String value;
  private List<String> expressions;
  private String metadata;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public List<String> getExpressions() {
    return expressions;
  }

  public void setExpressions(List<String> expressions) {
    this.expressions = expressions;
  }

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

}
