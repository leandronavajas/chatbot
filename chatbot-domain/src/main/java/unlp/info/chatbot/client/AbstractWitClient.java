package unlp.info.chatbot.client;

import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.response.WitResponse;
import unlp.info.chatbot.exception.WitApiException;

import java.io.IOException;
import java.net.URI;

public abstract class AbstractWitClient<R, H extends HttpRequestBase, O extends WitResponse> implements Client<R, O> {

  private static final String WIT_TOKEN = "Bearer 5ZPMSARKQ2QP2NBA4465WS27AZ43DENC";
  protected static final String WIT_VERSION = "20170307";

  @Override
  public O call(R request) {
    CloseableHttpClient httpClient = HttpClients.createDefault();

    H httpRequest = this.getHttpRequest();

    String url = this.getUrl(request);
    URI uri = URI.create(url);
    httpRequest.setURI(uri);

    httpRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

    httpRequest.setHeader("Authorization", WIT_TOKEN);

    this.addExtraToHttpRequest(httpRequest, request);

    String httpResponse = null;
    ResponseHandler<String> responseHandler = new BasicResponseHandler();

    try {
        httpResponse = httpClient.execute(httpRequest, responseHandler);

    } catch (IOException e) {
        throw new WitApiException("[WIT CLIENT] An error occurred while calling Wit Api Client. Message: " + e.getMessage());

    } finally {
      try {
        httpClient.close();
      } catch (IOException e) {
        throw new WitApiException("[WIT CLIENT] An error occurred while closing Wit Api Client. Message: " + e.getMessage());
      }

    }

    O response = this.getParser().parse(httpResponse);

    return response;

  }

  protected abstract H getHttpRequest();

  protected abstract String getUrl(R request);

  protected abstract void addExtraToHttpRequest(H httpRequest, R request);

  protected abstract Logger getLogger();

  protected abstract Parser<String,O> getParser();

}
