package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.body.PostEntityWitBody;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.request.AddEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Component
public class AddEntityWitClient extends AbstractWitClient<AddEntityWitRequest, HttpPost, AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddEntityWitClient.class);
  private static final String WIT_POST_ENTITY_URL = "https://api.wit.ai/entities?v=" + WIT_VERSION;

  private Parser<String, AddEntityWitResponse> createEntityResponseParser;

  @Override
  protected HttpPost getHttpRequest() {
    return new HttpPost();
  }

  @Override
  protected String getUrl(AddEntityWitRequest request) {
    return WIT_POST_ENTITY_URL;
  }

  @Override
  protected void addExtraToHttpRequest(HttpPost httpRequest, AddEntityWitRequest request) {

    // String bodyExample = "{\"doc\":\"A city that I like - BY APP\",\"id\":\"favorite_city_by_app\"}";

    // TODO: LN chequear si funciona esto y crear Transformer
    PostEntityWitBody body = new PostEntityWitBody();
    body.setId(request.getEntity());
    body.setDoc(request.getDescription());

    String bodyExample = body.toString();

    try {
      httpRequest.setEntity(new StringEntity(bodyExample));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected Parser<String, AddEntityWitResponse> getParser() {
    return this.createEntityResponseParser;
  }

  @Resource
  public void setCreateEntityResponseParser(Parser<String, AddEntityWitResponse> createEntityResponseParser) {
    this.createEntityResponseParser = createEntityResponseParser;
  }
}
