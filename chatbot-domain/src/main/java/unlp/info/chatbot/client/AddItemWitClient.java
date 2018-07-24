package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.AbstractAddEntityWitClient;
import unlp.info.chatbot.client.body.AddItemWitBody;
import unlp.info.chatbot.client.request.AddItemWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;

import java.io.UnsupportedEncodingException;

@Component
public class AddItemWitClient extends AbstractAddEntityWitClient<AddItemWitRequest, AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddItemWitClient.class);
  private static final String WIT_ADD_ITEM_URL = "https://api.wit.ai/entities/%s/values?v=" + WIT_VERSION;


  @Override
  protected String getUrl(AddItemWitRequest request) {
    return String.format(WIT_ADD_ITEM_URL, request.getEntity());
  }

  @Override
  protected void addExtraToHttpRequest(HttpPost httpRequest, AddItemWitRequest request) {
    AddItemWitBody body = new AddItemWitBody();
    body.setValue(request.getItemId());

    // TODO: LN analizar si hay que settear en la creacion
    //body.setExpressions();
    //body.setMetadata();

    // example
    // {"value":"London", "expressions":["London"], "metadata":"CITY_1234"}'

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
