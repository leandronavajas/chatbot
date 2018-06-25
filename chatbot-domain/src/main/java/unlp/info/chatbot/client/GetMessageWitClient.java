package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.helper.WitClientHelper;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.request.GetMessageWitRequest;
import unlp.info.chatbot.client.response.WitMessageResponse;

import javax.annotation.Resource;

@Component
public class GetMessageWitClient extends AbstractWitClient<GetMessageWitRequest, HttpGet, WitMessageResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetMessageWitClient.class);
  private static final String WIT_GET_MESSAGE_URL = "https://api.wit.ai/message?v=20170307&q=";

  private Parser<String,WitMessageResponse> witMessageResponseParser;

  @Override
  protected HttpGet getHttpRequest() {
    return new HttpGet();
  }

  @Override
  protected String getUrl(GetMessageWitRequest request) {
    String message = WitClientHelper.replaceSpaces(request.getMessage());
    return WIT_GET_MESSAGE_URL + message;
  }

  @Override
  protected void addExtraToHttpRequest(HttpGet httpRequest, GetMessageWitRequest request) {
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected Parser<String, WitMessageResponse> getParser() {
    return this.witMessageResponseParser;
  }

  @Resource
  public void setWitMessageResponseParser(Parser<String, WitMessageResponse> witMessageResponseParser) {
    this.witMessageResponseParser = witMessageResponseParser;
  }
}
