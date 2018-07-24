package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.AbstractAddEntityWitClient;
import unlp.info.chatbot.client.body.AddExpressionWitBody;
import unlp.info.chatbot.client.request.AddExpressionWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;

import java.io.UnsupportedEncodingException;

@Component
public class AddExpressionWitClient extends AbstractAddEntityWitClient<AddExpressionWitRequest, AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddExpressionWitClient.class);
  private static final String WIT_ADD_EXPRESSION_URL = "https://api.wit.ai/entities/%s/values/%s/expressions?v=" + WIT_VERSION;

  @Override
  protected String getUrl(AddExpressionWitRequest request) {
    return String.format(WIT_ADD_EXPRESSION_URL, request.getEntity(), request.getItemId());
  }

  @Override
  protected void addExtraToHttpRequest(HttpPost httpRequest, AddExpressionWitRequest request) {
    AddExpressionWitBody body = new AddExpressionWitBody();
    body.setExpression(request.getExpressionId());

    String bodyJsonFormat = body.toString();

    try {
      httpRequest.setEntity(new StringEntity(bodyJsonFormat));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
