package unlp.info.chatbot.service;

import unlp.info.chatbot.client.request.*;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.client.response.WitMessageResponse;

public interface WitService {

  AddEntityWitResponse addCategory(AddCategoryWitRequest request);

  AddEntityWitResponse addItem(AddItemWitRequest request);

  AddEntityWitResponse addPhrase(AddPhraseWitRequest request);

  AddEntityWitResponse updateEntity(PutEntityWitRequest request);

  WitMessageResponse obtainMessage(GetMessageWitRequest request);

}
