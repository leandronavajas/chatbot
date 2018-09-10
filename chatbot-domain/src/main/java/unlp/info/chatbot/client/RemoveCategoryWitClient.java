package unlp.info.chatbot.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.request.RemoveCategoryWitClientRequest;
import unlp.info.chatbot.client.response.RemoveEntityWitClientResponse;

import javax.annotation.Resource;

@Component
public class RemoveCategoryWitClient extends AbstractRemoveEntityWitClient<RemoveCategoryWitClientRequest, RemoveEntityWitClientResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveCategoryWitClient.class);
  private static final String WIT_REMOVE_CATEGORY_URL = "https://api.wit.ai/entities/%s?v=" + WIT_VERSION;

  private Parser<String, RemoveEntityWitClientResponse> removeEntityResponseParser;

  @Override
  protected String getUrl(RemoveCategoryWitClientRequest request) {
    return String.format(WIT_REMOVE_CATEGORY_URL, request.getCategoryId());
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected Parser<String, RemoveEntityWitClientResponse> getParser() {
    return this.removeEntityResponseParser;
  }

  @Resource
  public void setRemoveEntityResponseParser(Parser<String, RemoveEntityWitClientResponse> removeEntityResponseParser) {
    this.removeEntityResponseParser = removeEntityResponseParser;
  }
}
