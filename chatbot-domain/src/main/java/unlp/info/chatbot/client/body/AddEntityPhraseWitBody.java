package unlp.info.chatbot.client.body;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AddEntityPhraseWitBody {

  private String entity;
  private String value;

  public AddEntityPhraseWitBody(String entity, String value) {
    this.entity = entity;
    this.value = value;
  }

  public String getEntity() {
    return entity;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }

}
