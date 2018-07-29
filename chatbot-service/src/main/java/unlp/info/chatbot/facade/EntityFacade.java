package unlp.info.chatbot.facade;

import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.dto.MessageDTO;

import java.util.List;

public interface EntityFacade {

  MessageDTO addCategory(AddEntityBody body);

  MessageDTO addItem(String categoryId, AddEntityBody body);

  MessageDTO addExpression(String categoryId, String itemId, AddEntityBody body);

  List<MessageDTO> getAll(String filter);

  List<MessageDTO> getItemsForCategory(String categoryId);

  List<MessageDTO> getExpressionsForItem(String categoryId, String itemId);
}
