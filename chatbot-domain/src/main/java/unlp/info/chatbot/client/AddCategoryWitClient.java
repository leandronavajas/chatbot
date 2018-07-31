package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.AbstractAddEntityWitClient;
import unlp.info.chatbot.client.body.AddCategoryWitBody;
import unlp.info.chatbot.client.request.AddCategoryWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;

import java.io.UnsupportedEncodingException;


@Component
public class AddCategoryWitClient extends AbstractAddEntityWitClient<AddCategoryWitRequest, AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddCategoryWitClient.class);
  private static final String WIT_ADD_CATEGORY_URL = "https://api.wit.ai/entities?v=" + WIT_VERSION;

  @Override
  protected String getUrl(AddCategoryWitRequest request) {
    return WIT_ADD_CATEGORY_URL;
  }

  @Override
  protected void addExtraToHttpRequest(HttpPost httpRequest, AddCategoryWitRequest request) {

    AddCategoryWitBody body = new AddCategoryWitBody();
    body.setId(request.getEntity());
    body.setDoc(request.getDescription());

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
