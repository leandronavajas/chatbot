package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.body.PutCategoryBody;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.request.PutEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.exception.ParseWitBodyException;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Component
public class PutEntityWitClient extends AbstractWitClient<PutEntityWitRequest, HttpPut, AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(PutEntityWitClient.class);
  private static final String WIT_PUT_CATEGORY_URL = "https://api.wit.ai/entities/%s?v=" + WIT_VERSION;

  private Parser<String, AddEntityWitResponse> createEntityResponseParser;


  @Override
  protected String getUrl(PutEntityWitRequest request) {
    return String.format(WIT_PUT_CATEGORY_URL, request.getEntityName());
  }

  @Override
  protected HttpPut getHttpRequest() {
    return new HttpPut();
  }

  @Override
  protected void addExtraToHttpRequest(HttpPut httpRequest, PutEntityWitRequest request) {
    PutCategoryBody body = new PutCategoryBody();
    body.setLookups(request.getLookups());

    String bodyJsonFormat = body.toString();

    try {
      httpRequest.setEntity(new StringEntity(bodyJsonFormat));
    } catch (UnsupportedEncodingException e) {
      throw new ParseWitBodyException("[PARSER] An error occurred while parsing the Wit body");
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
