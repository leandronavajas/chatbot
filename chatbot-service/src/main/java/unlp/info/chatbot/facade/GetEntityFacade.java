package unlp.info.chatbot.facade;

import unlp.info.chatbot.dto.MessageDTO;

import java.util.List;

public interface GetEntityFacade {

  List<MessageDTO> getAll(String filter);

  List<MessageDTO> getItemsForCategory(String categoryId);

  List<MessageDTO> getSynonymsForItem(String categoryId, String itemId);

  List<MessageDTO> getPhrasesForItem(String categoryId, String itemId);
}
