package unlp.info.chatbot.facade;

import org.springframework.http.ResponseEntity;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.model.RemoveStatus;

import java.util.List;

public interface MessageFacade {

  MessageDTO getMessage(String phrase);

  ResponseEntity<RemoveStatus> removeMessage(String entity);

  List<MessageDTO> getAll();

}
