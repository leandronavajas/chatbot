package unlp.info.chatbot.facade;

import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.dto.MessageDTO;

public interface AddEntityFacade {

  MessageDTO addCategory(AddEntityBody body);

  MessageDTO addItem(String categoryId, AddEntityBody body);

  MessageDTO addSynonym(String categoryId, String itemId, AddEntityBody body);

  MessageDTO addPhrase(String categoryId, String itemId, AddEntityBody body);

}
