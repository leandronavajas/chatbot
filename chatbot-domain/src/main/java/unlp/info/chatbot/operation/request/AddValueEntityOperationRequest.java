package unlp.info.chatbot.operation.request;

import unlp.info.chatbot.client.request.AddValueEntityWitRequest;

public class AddValueEntityOperationRequest {

  private String entity;
  private AddValueEntityWitRequest body;

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public AddValueEntityWitRequest getBody() {
    return body;
  }

  public void setBody(AddValueEntityWitRequest body) {
    this.body = body;
  }
}
