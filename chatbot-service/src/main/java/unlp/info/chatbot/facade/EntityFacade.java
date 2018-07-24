package unlp.info.chatbot.facade;

import unlp.info.chatbot.client.request.AddValueEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.dto.MessageDTO;

public interface EntityFacade {

  MessageDTO addCategory(AddEntityBody body);

  MessageDTO addItem(String categoryId, AddEntityBody body);

  MessageDTO addExpression(String categoryId, String itemId, AddEntityBody body);

  AddEntityWitResponse addValueForEntity(String entity, AddValueEntityWitRequest request);
}
