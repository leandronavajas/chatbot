package unlp.info.chatbot.facade;

import org.springframework.http.ResponseEntity;
import unlp.info.chatbot.controller.body.AddMessageBody;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.model.RemoveStatus;

import java.math.BigDecimal;
import java.util.List;

public interface MessageFacade {

  MessageDTO getMessage(String entity, BigDecimal confidence);

  MessageDTO addMessage(AddMessageBody body);

  ResponseEntity<RemoveStatus> removeMessage(String entity);

  List<MessageDTO> getAll();

}
