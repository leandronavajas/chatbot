package unlp.info.chatbot.facade;

import unlp.info.chatbot.dto.MessageDTO;

public interface MessageFacade {

  MessageDTO getMessage(String phrase);

}
