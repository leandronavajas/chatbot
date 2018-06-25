package unlp.info.chatbot.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unlp.info.chatbot.client.response.AddEntityWitResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

// TODO: LN eliminar esta clase
public class WitClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(WitClient.class);
  private static final String WIT_VERSION = "20170307";
  private static final String WIT_TOKEN = "Bearer 5ZPMSARKQ2QP2NBA4465WS27AZ43DENC";

  private static final String WIT_GET_ENTITY_URL = "https://api.wit.ai/entities/";
  private static final String WIT_POST_ENTITY_URL = "https://api.wit.ai/entities?v=" + WIT_VERSION;

  public static void main(String[] args) {

    WitClient witClient = new WitClient();
    //witClient.get();
    witClient.post();

  }

  public void get() {
    CloseableHttpClient httpClient = HttpClients.createDefault();

    String entityName = "tema";

    HttpGet httpGet = new HttpGet(WIT_GET_ENTITY_URL + entityName + "?v=" + WIT_VERSION);
    httpGet.setHeader("Authorization", WIT_TOKEN);

    String response = null;
    try {
      ResponseHandler<String> responseHandler = new BasicResponseHandler();
      response = httpClient.execute(httpGet, responseHandler);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // TODO: LN dividir la logica de call a la API de wit de la deserializacion
    this.parse(response);
  }

  public void post() {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(WIT_POST_ENTITY_URL);
    httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    httpPost.setHeader("Authorization", WIT_TOKEN);

    try {
      // TODO: LN crear un StringBuilder o alguna magia para crear objetos
      String bodyExample = "{\"doc\":\"A city that I like - BY APP\",\"id\":\"favorite_city_by_app\"}";
      httpPost.setEntity(new StringEntity(bodyExample));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    String response = null;
    try {
      ResponseHandler<String> responseHandler = new BasicResponseHandler();
      response = httpClient.execute(httpPost, responseHandler);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // TODO: LN dividir la logica de call a la API de wit de la deserializacion
    this.parse(response);
  }




  private void parse(String response) {
    ObjectMapper objectMapper = new ObjectMapper();
    AddEntityWitResponse addEntityWitResponse = null;
    try {
      addEntityWitResponse = objectMapper.readValue(response, AddEntityWitResponse.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    LOGGER.info("Response --> {}", addEntityWitResponse);

  }

}
