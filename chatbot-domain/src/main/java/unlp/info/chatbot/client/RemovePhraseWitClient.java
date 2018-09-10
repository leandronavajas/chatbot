package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.body.RemovePhraseWitBody;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.request.RemovePhraseWitClientRequest;
import unlp.info.chatbot.client.response.RemovePhraseWitClientResponse;
import unlp.info.chatbot.exception.ParseWitBodyException;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Component
public class RemovePhraseWitClient extends AbstractWitClient<RemovePhraseWitClientRequest, HttpPost, RemovePhraseWitClientResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemovePhraseWitClient.class);
  private static final String WIT_REMOVE_PHRASE_URL = "https://api.wit.ai/samples?v=" + WIT_VERSION;

  private Parser<String, RemovePhraseWitClientResponse> removePhraseResponseParser;

  @Override
  protected HttpPost getHttpRequest() {
    return new HttpPost();
  }

  @Override
  protected String getUrl(RemovePhraseWitClientRequest request) {
    return WIT_REMOVE_PHRASE_URL;
  }

  @Override
  protected void addExtraToHttpRequest(HttpPost httpRequest, RemovePhraseWitClientRequest request) {
    // TODO: LN -> HttpDelete de Apache no acepta un body en el delete. Pruebo con un Post
    // De no funcionar, subclasear el HttpPost de apache y overridear el getMethod

    RemovePhraseWitBody body = new RemovePhraseWitBody(request.getPhrase());

    String bodyJsonFormat = body.format();
    String bodyJsonFormatAsList = "[" + bodyJsonFormat + "]";

    try {
      httpRequest.setEntity(new StringEntity(bodyJsonFormatAsList));
    } catch (UnsupportedEncodingException e) {
      throw new ParseWitBodyException("[PARSER] An error occurred while parsing the Wit body");
    }
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected Parser<String, RemovePhraseWitClientResponse> getParser() {
    return this.removePhraseResponseParser;
  }



  @Resource
  public void setRemovePhraseResponseParser(Parser<String, RemovePhraseWitClientResponse> removePhraseResponseParser) {
    this.removePhraseResponseParser = removePhraseResponseParser;
  }
}
