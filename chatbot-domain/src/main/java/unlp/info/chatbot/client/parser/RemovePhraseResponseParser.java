package unlp.info.chatbot.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.response.RemovePhraseWitClientResponse;

import java.io.IOException;

@Component
public class RemovePhraseResponseParser extends AbstractClientResponseParser<RemovePhraseWitClientResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemovePhraseResponseParser.class);

  @Override
  protected RemovePhraseWitClientResponse readObjectFrom(String in, ObjectMapper objectMapper) throws IOException {
    return objectMapper.readValue(in, RemovePhraseWitClientResponse.class);
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
