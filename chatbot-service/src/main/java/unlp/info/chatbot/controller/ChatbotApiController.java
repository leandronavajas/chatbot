package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.controller.body.AddResponseBody;
import unlp.info.chatbot.dto.ResponseDTO;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.facade.ChatbotApiFacade;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("response")
public class ChatbotApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChatbotApiController.class);

  private ChatbotApiFacade chatbotApiFacade;

  @RequestTracking
  @GetMapping("/{entity}")
  public ResponseDTO getResponse(@PathVariable String entity, @NotNull BigDecimal confidence) {
    LOGGER.info("Request -> entity: {} - confidence: {}", entity, confidence);

    return this.chatbotApiFacade.getResponse(entity, confidence);
  }

  @RequestTracking
  @PostMapping
  public ResponseDTO add(@RequestBody AddResponseBody body) {
    LOGGER.info("Add Response: {}", body);

    return this.chatbotApiFacade.addResponse(body);
  }

  @RequestTracking
  @DeleteMapping("/{entity}")
  public ResponseEntity<StatusResponse> remove(@PathVariable String entity) {
    LOGGER.info("Remove Entity with id: {}", entity);

    return this.chatbotApiFacade.removeResponse(entity);
  }

  @RequestTracking
  @GetMapping
  public List<ResponseDTO> getAll() {
    LOGGER.info("Get all elements");

    return this.chatbotApiFacade.getAll();
  }

  @Resource
  public void setChatbotApiFacade(ChatbotApiFacade chatbotApiFacade) {
    this.chatbotApiFacade = chatbotApiFacade;
  }
}
