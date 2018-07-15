package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.body.AddValueEntityWitBody;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.request.AddValueEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.operation.request.AddValueEntityOperationRequest;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Component
public class AddValueEntityWitClient extends AbstractWitClient<AddValueEntityOperationRequest, HttpPost, AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddValueEntityWitClient.class);
  private static final String WIT_POST_VALUE_ENTITY_URL = "https://api.wit.ai/entities/%s/values?v=" + WIT_VERSION;

  private Parser<String, AddEntityWitResponse> createEntityResponseParser;

  @Override
  protected HttpPost getHttpRequest() {
    return new HttpPost();
  }

  @Override
  protected String getUrl(AddValueEntityOperationRequest request) {
    return String.format(WIT_POST_VALUE_ENTITY_URL, request.getEntity());
  }

  @Override
  protected void addExtraToHttpRequest(HttpPost httpRequest, AddValueEntityOperationRequest request) {
    AddValueEntityWitBody body = new AddValueEntityWitBody();
    body.setValue(request.getBody().getValue());
    body.setMetadata(request.getBody().getMetadata());
    body.setExpressions(request.getBody().getExpressions());

    // TODO: LN chequear este toString -> puede romper las expressions
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
