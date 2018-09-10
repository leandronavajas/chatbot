package unlp.info.chatbot.client.response;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RemovePhraseWitClientResponse implements WitResponse {

  private String sent;
  private String n;

  public String getSent() {
    return sent;
  }

  public void setSent(String sent) {
    this.sent = sent;
  }

  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
