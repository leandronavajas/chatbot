package unlp.info.chatbot.client.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import unlp.info.chatbot.exception.ParseResponseException;

import java.io.IOException;

public abstract class AbstractClientResponseParser<O> implements Parser<String,O>{
  @Override
  public O parse(String in) {
    ObjectMapper objectMapper = new ObjectMapper();
    O parsedResponse = null;
    try {
      parsedResponse = readObjectFrom(in, objectMapper);
    } catch (IOException e) {
      throw new ParseResponseException("[PARSER] An error occurred while parsing Response");
    }
    this.getLogger().info("Response --> {}", parsedResponse);
    return parsedResponse;
  }

  protected abstract O readObjectFrom(String in, ObjectMapper objectMapper) throws IOException;


  protected abstract Logger getLogger();
}
