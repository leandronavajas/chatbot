package unlp.info.chatbot.client;

import com.google.common.collect.Lists;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.body.AddEntityPhraseWitBody;
import unlp.info.chatbot.client.body.AddPhraseWitBody;
import unlp.info.chatbot.client.request.AddPhraseWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.exception.ParseWitBodyException;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
public class AddPhraseWitClient extends AbstractAddEntityWitClient<AddPhraseWitRequest, AddEntityWitResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddPhraseWitClient.class);
  private static final String WIT_ADD_PHRASE_URL = "https://api.wit.ai/samples?v=" + WIT_VERSION;

  private static final String CATEGORIES = "categories";

  @Override
  protected String getUrl(AddPhraseWitRequest request) {
    return WIT_ADD_PHRASE_URL;
  }

  @Override
  protected void addExtraToHttpRequest(HttpPost httpRequest, AddPhraseWitRequest request) {
    AddPhraseWitBody body = new AddPhraseWitBody();
    body.setText(request.getDescription());

    List<AddEntityPhraseWitBody> entities = Lists.newArrayList();
    AddEntityPhraseWitBody addEntityPhraseBody = new AddEntityPhraseWitBody(CATEGORIES, request.getEntity());
    entities.add(addEntityPhraseBody);
    
    body.setEntities(entities);

    List<AddPhraseWitBody> bodyList = Lists.newArrayList(body);

    String bodyJsonFormat = bodyList.toString();

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
