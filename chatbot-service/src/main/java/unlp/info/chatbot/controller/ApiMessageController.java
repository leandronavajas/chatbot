package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.facade.MessageFacade;
import unlp.info.chatbot.model.RemoveStatus;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("message")
public class ApiMessageController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApiMessageController.class);

  private MessageFacade messageFacade;

  @RequestTracking
  @GetMapping()
  public MessageDTO get(@RequestParam String phrase) {
    LOGGER.info("Request -> phrase: {}", phrase);

    return this.messageFacade.getMessage(phrase);
  }

  @RequestTracking
  @DeleteMapping("/{entity}")
  public ResponseEntity<RemoveStatus> remove(@PathVariable String entity) {
    LOGGER.info("Remove Entity with id: {}", entity);

    return this.messageFacade.removeMessage(entity);
  }

  @RequestTracking
  @GetMapping("/all")
  public List<MessageDTO> getAll() {
    LOGGER.info("Get all messages");

    return this.messageFacade.getAll();
  }

  @Resource
  public void setMessageFacade(MessageFacade messageFacade) {
    this.messageFacade = messageFacade;
  }
}
