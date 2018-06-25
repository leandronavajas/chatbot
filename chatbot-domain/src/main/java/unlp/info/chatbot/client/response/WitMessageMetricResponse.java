package unlp.info.chatbot.client.response;

import java.math.BigDecimal;

public class WitMessageMetricResponse implements WitResponse {

  private String metadata;

  private String value;

  private BigDecimal confidence;

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public BigDecimal getConfidence() {
    return confidence;
  }

  public void setConfidence(BigDecimal confidence) {
    this.confidence = confidence;
  }
}
