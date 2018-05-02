package unlp.info.chatbot.operation.request;

import java.math.BigDecimal;

public class GetResponseRequest {

  private String entity;

  private BigDecimal confidence;

  public GetResponseRequest(String entity, BigDecimal confidence) {
    this.entity = entity;
    this.confidence = confidence;
  }

  public String getEntity() {
    return entity;
  }

  public BigDecimal getConfidence() {
    return confidence;
  }
}
