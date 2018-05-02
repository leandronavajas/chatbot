package unlp.info.chatbot.facade;

import org.springframework.http.ResponseEntity;
import unlp.info.chatbot.controller.body.AddResponseBody;
import unlp.info.chatbot.dto.ResponseDTO;
import unlp.info.chatbot.dto.StatusResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ChatbotApiFacade {

  ResponseDTO getResponse(String entity, BigDecimal confidence);

  ResponseDTO addResponse(AddResponseBody body);

  ResponseEntity<StatusResponse> removeResponse(String entity);

  List<ResponseDTO> getAll();

}
