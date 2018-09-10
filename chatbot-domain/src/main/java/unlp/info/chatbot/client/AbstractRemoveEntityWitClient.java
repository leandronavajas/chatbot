package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpDelete;
import unlp.info.chatbot.client.response.WitResponse;

public abstract class AbstractRemoveEntityWitClient<R, O extends WitResponse> extends AbstractWitClient<R, HttpDelete, O> {

  @Override
  protected HttpDelete getHttpRequest() {
    return new HttpDelete();
  }

  @Override
  protected void addExtraToHttpRequest(HttpDelete httpRequest, R request) {
  }

}
