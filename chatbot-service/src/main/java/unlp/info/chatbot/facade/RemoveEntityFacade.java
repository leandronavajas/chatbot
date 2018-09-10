package unlp.info.chatbot.facade;

import unlp.info.chatbot.dto.StatusResponse;

public interface RemoveEntityFacade {

  StatusResponse removeCategory(String categoryId);

  StatusResponse removeItem(String categoryId, String itemId);

  StatusResponse removeSynonym(String categoryId, String itemId, String synonymId);

  StatusResponse removePhrase(String categoryId, String itemId, String phrase);

}
