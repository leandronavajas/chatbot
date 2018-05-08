package unlp.info.chatbot.operation.request;

import java.math.BigDecimal;

public class GetMessageRequest {

  private String entity;

  private BigDecimal confidence;

  public GetMessageRequest(String entity, BigDecimal confidence) {
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
