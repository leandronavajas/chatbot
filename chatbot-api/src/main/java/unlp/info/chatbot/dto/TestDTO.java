package unlp.info.chatbot.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TestDTO implements PersistentObject {

  private String id;
  private String description;

  public TestDTO(String id, String description) {
    this.id = id;
    this.description = description;
  }

  @Override
  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
