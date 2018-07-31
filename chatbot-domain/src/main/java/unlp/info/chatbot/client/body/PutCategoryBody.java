package unlp.info.chatbot.client.body;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class PutCategoryBody {

  private List<String> lookups;

  public List<String> getLookups() {
    return lookups;
  }

  public void setLookups(List<String> lookups) {
    this.lookups = lookups;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
