package unlp.info.chatbot.facade;

import org.springframework.http.ResponseEntity;
import unlp.info.chatbot.controller.body.AddItemBody;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.dto.TestDTO;

import java.util.List;

public interface ApiFacade {

  TestDTO get(String id);

  TestDTO add(AddItemBody body);

  ResponseEntity<StatusResponse> remove(String id);

  List<TestDTO> get();
}
