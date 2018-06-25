package unlp.info.chatbot.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class WitMessageResponse implements WitResponse {

  @JsonProperty("msg_id")
  private String msgId;

  @JsonProperty("_text")
  private String text;

  private Map<String, List<WitMessageMetricResponse>> entities;

  public String getMsgId() {
    return msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Map<String, List<WitMessageMetricResponse>> getEntities() {
    return entities;
  }

  public void setEntities(Map<String, List<WitMessageMetricResponse>> entities) {
    this.entities = entities;
  }
}
