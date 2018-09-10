package unlp.info.chatbot.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.request.RemoveSynonymWitClientRequest;
import unlp.info.chatbot.client.response.RemoveSynonymWitClientResponse;

import javax.annotation.Resource;

@Component
public class RemoveSynonymWitClient extends AbstractRemoveEntityWitClient<RemoveSynonymWitClientRequest, RemoveSynonymWitClientResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveSynonymWitClient.class);
  private static final String WIT_REMOVE_SYNONYM_URL = "https://api.wit.ai/entities/%s/values/%s/expressions/%s?v=" + WIT_VERSION;

  private Parser<String, RemoveSynonymWitClientResponse> removeSynonymResponseParser;

  @Override
  protected String getUrl(RemoveSynonymWitClientRequest request) {
    return String.format(WIT_REMOVE_SYNONYM_URL, request.getCategoryId(), request.getItemId(), request.getSynonymId());
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected Parser<String, RemoveSynonymWitClientResponse> getParser() {
    return this.removeSynonymResponseParser;
  }

  @Resource
  public void setRemoveSynonymResponseParser(Parser<String, RemoveSynonymWitClientResponse> removeSynonymResponseParser) {
    this.removeSynonymResponseParser = removeSynonymResponseParser;
  }
}
