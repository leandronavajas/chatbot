package unlp.info.chatbot.facade;

import org.springframework.http.ResponseEntity;
import unlp.info.chatbot.controller.body.AddMessageBody;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.StatusResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ChatbotApiFacade {

  MessageDTO getMessage(String entity, BigDecimal confidence);

  MessageDTO addMessage(AddMessageBody body);

  ResponseEntity<StatusResponse> removeMessage(String entity);

  List<MessageDTO> getAll();

}
