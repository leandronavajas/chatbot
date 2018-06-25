package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.request.GetEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;

import javax.annotation.Resource;

@Component
public class GetEntityWitClient extends AbstractWitClient<GetEntityWitRequest, HttpGet, AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetEntityWitClient.class);

  private static final String WIT_GET_ENTITY_URL = "https://api.wit.ai/entities/";

  private Parser<String, AddEntityWitResponse> createEntityResponseParser;

  @Override
  protected HttpGet getHttpRequest() {
    return new HttpGet();
  }

  @Override
  protected String getUrl(GetEntityWitRequest request) {
    return WIT_GET_ENTITY_URL + request.getEntityName() + "?v=" + WIT_VERSION;
  }

  @Override
  protected void addExtraToHttpRequest(HttpGet httpRequest, GetEntityWitRequest request) {
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
