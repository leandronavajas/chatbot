package unlp.info.chatbot;

import org.apache.http.client.methods.HttpPost;
import unlp.info.chatbot.client.AbstractWitClient;
import unlp.info.chatbot.client.parser.Parser;
import unlp.info.chatbot.client.response.WitResponse;

import javax.annotation.Resource;

public abstract class AbstractAddEntityWitClient<R, O extends WitResponse> extends AbstractWitClient<R, HttpPost, O> {

  private Parser<String, O> createEntityResponseParser;

  @Override
  protected HttpPost getHttpRequest() {
    return new HttpPost();
  }

  @Override
  protected Parser<String, O> getParser() {
    return this.createEntityResponseParser;
  }

  @Resource
  public void setCreateEntityResponseParser(Parser<String, O> createEntityResponseParser) {
    this.createEntityResponseParser = createEntityResponseParser;
  }

}
