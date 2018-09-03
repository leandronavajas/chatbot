package unlp.info.chatbot.client.body;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class AddPhraseWitBody {

  private String text;
  private List<AddEntityPhraseWitBody> entities;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<AddEntityPhraseWitBody> getEntities() {
    return entities;
  }

  public void setEntities(List<AddEntityPhraseWitBody> entities) {
    this.entities = entities;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
