package unlp.info.chatbot.service;

import unlp.info.chatbot.client.request.GetEntityWitRequest;
import unlp.info.chatbot.client.request.GetMessageWitRequest;
import unlp.info.chatbot.client.request.AddEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.client.response.WitMessageResponse;

public interface WitService {

  AddEntityWitResponse obtainEntity(GetEntityWitRequest request);

  void addEntity(AddEntityWitRequest request);

  WitMessageResponse obtainMessage(GetMessageWitRequest request);

}
