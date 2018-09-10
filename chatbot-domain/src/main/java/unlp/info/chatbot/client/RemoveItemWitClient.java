package unlp.info.chatbot.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.request.RemoveItemWitClientRequest;
import unlp.info.chatbot.client.response.RemoveEntityWitClientResponse;

import javax.annotation.Resource;

@Component
public class RemoveItemWitClient extends AbstractRemoveEntityWitClient<RemoveItemWitClientRequest, RemoveEntityWitClientResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveItemWitClient.class);
  private static final String WIT_REMOVE_ITEM_URL = "https://api.wit.ai/entities/%s/values/%s?v=" + WIT_VERSION;

  private Parser<String,RemoveEntityWitClientResponse> removeEntityResponseParser;

  @Override
  protected String getUrl(RemoveItemWitClientRequest request) {
    return String.format(WIT_REMOVE_ITEM_URL, request.getCategoryId(), request.getItemId());
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
