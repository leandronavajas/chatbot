package unlp.info.chatbot.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.response.RemoveEntityWitClientResponse;

import java.io.IOException;

@Component
public class RemoveEntityResponseParser extends AbstractClientResponseParser<RemoveEntityWitClientResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveEntityResponseParser.class);

  @Override
  protected RemoveEntityWitClientResponse readObjectFrom(String in, ObjectMapper objectMapper) throws IOException {
    return objectMapper.readValue(in, RemoveEntityWitClientResponse.class);
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
