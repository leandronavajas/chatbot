package unlp.info.chatbot.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.response.AddEntityWitResponse;

import java.io.IOException;

@Component
public class CreateEntityResponseParser extends AbstractClientResponseParser<AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(CreateEntityResponseParser.class);

  @Override
  protected AddEntityWitResponse readObjectFrom(String in, ObjectMapper objectMapper) throws IOException {
    return objectMapper.readValue(in, AddEntityWitResponse.class);
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
