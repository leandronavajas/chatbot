package unlp.info.chatbot.facade;

import unlp.info.chatbot.client.request.AddValueEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.dto.MessageDTO;

public interface EntityFacade {

  MessageDTO addEntity(AddEntityBody body);

  AddEntityWitResponse addValueForEntity(String entity, AddValueEntityWitRequest request);
}
