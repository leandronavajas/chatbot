package unlp.info.chatbot.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.response.WitMessageResponse;

import java.io.IOException;

@Component
public class WitMessageResponseParser extends AbstractClientResponseParser<WitMessageResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(WitMessageResponseParser.class);

  @Override
  protected WitMessageResponse readObjectFrom(String in, ObjectMapper objectMapper) throws IOException {
    return objectMapper.readValue(in, WitMessageResponse.class);
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
