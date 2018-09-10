package unlp.info.chatbot.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.response.RemoveSynonymWitClientResponse;

import java.io.IOException;

@Component
public class RemoveSynonymResponseParser extends AbstractClientResponseParser<RemoveSynonymWitClientResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveSynonymResponseParser.class);

  @Override
  protected RemoveSynonymWitClientResponse readObjectFrom(String in, ObjectMapper objectMapper) throws IOException {
    return objectMapper.readValue(in, RemoveSynonymWitClientResponse.class);
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
