package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.controller.body.AddMessageBody;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.facade.MessageFacade;
import unlp.info.chatbot.model.RemoveStatus;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("message")
public class ApiMessageController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApiMessageController.class);

  private MessageFacade messageFacade;

  @RequestTracking
  @GetMapping("/{entity}")
  public MessageDTO get(@PathVariable String entity, @NotNull BigDecimal confidence) {
    LOGGER.info("Request -> entity: {} - confidence: {}", entity, confidence);

    return this.messageFacade.getMessage(entity, confidence);
  }

  @RequestTracking
  @PostMapping
  public MessageDTO add(@RequestBody AddMessageBody body) {
    LOGGER.info("Add Message: {}", body);

    return this.messageFacade.addMessage(body);
  }

  @RequestTracking
  @DeleteMapping("/{entity}")
  public ResponseEntity<RemoveStatus> remove(@PathVariable String entity) {
    LOGGER.info("Remove Entity with id: {}", entity);

    return this.messageFacade.removeMessage(entity);
  }

  @RequestTracking
  @GetMapping
  public List<MessageDTO> getAll() {
    LOGGER.info("Get all messages");

    return this.messageFacade.getAll();
  }

  @Resource
  public void setMessageFacade(MessageFacade messageFacade) {
    this.messageFacade = messageFacade;
  }
}
