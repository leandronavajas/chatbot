package unlp.info.chatbot.service;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.Client;
import unlp.info.chatbot.client.request.GetEntityWitRequest;
import unlp.info.chatbot.client.request.GetMessageWitRequest;
import unlp.info.chatbot.client.request.AddEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.client.response.WitMessageResponse;

import javax.annotation.Resource;

@Component
public class WitServiceImpl implements WitService {

  private Client<GetEntityWitRequest, AddEntityWitResponse> getEntityWitClient;

  private Client<AddEntityWitRequest, AddEntityWitResponse> postEntityWitClient;

  private Client<GetMessageWitRequest, WitMessageResponse> getMessageWitClient;

  @Override
  public AddEntityWitResponse obtainEntity(GetEntityWitRequest request) {
    return this.getEntityWitClient.call(request);
  }

  @Override
  public void addEntity(AddEntityWitRequest request) {
    this.postEntityWitClient.call(request);
  }

  @Override
  public WitMessageResponse obtainMessage(GetMessageWitRequest request) {
    return this.getMessageWitClient.call(request);
  }


  @Resource
  public void setGetEntityWitClient(Client<GetEntityWitRequest, AddEntityWitResponse> getEntityWitClient) {
    this.getEntityWitClient = getEntityWitClient;
  }

  @Resource
  public void setPostEntityWitClient(Client<AddEntityWitRequest, AddEntityWitResponse> postEntityWitClient) {
    this.postEntityWitClient = postEntityWitClient;
  }

  @Resource
  public void setGetMessageWitClient(Client<GetMessageWitRequest, WitMessageResponse> getMessageWitClient) {
    this.getMessageWitClient = getMessageWitClient;
  }
}
