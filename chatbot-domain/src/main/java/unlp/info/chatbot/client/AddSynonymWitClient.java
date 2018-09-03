package unlp.info.chatbot.client;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.AbstractAddEntityWitClient;
import unlp.info.chatbot.client.body.AddSynonymWitBody;
import unlp.info.chatbot.client.request.AddSynonymWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.exception.ParseWitBodyException;

import java.io.UnsupportedEncodingException;

@Component
public class AddSynonymWitClient extends AbstractAddEntityWitClient<AddSynonymWitRequest, AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddSynonymWitClient.class);
  private static final String WIT_ADD_SYNONYM_URL = "https://api.wit.ai/entities/%s/values/%s/expressions?v=" + WIT_VERSION;

  @Override
  protected String getUrl(AddSynonymWitRequest request) {
    return String.format(WIT_ADD_SYNONYM_URL, request.getEntity(), request.getItemId());
  }

  @Override
  protected void addExtraToHttpRequest(HttpPost httpRequest, AddSynonymWitRequest request) {
    AddSynonymWitBody body = new AddSynonymWitBody();
    body.setExpression(request.getSynonymId());

    String bodyJsonFormat = body.format();

    try {
      httpRequest.setEntity(new StringEntity(bodyJsonFormat));
    } catch (UnsupportedEncodingException e) {
      throw new ParseWitBodyException("[PARSER] An error occurred while parsing the Wit body");
    }

  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
