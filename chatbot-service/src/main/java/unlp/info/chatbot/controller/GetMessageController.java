package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.facade.MessageFacade;

import javax.annotation.Resource;

@RestController
@RequestMapping("message")
public class GetMessageController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetMessageController.class);

  private MessageFacade messageFacade;

  @RequestTracking
  @GetMapping()
  public MessageDTO get(@RequestParam String phrase) {
    LOGGER.info("Request -> phrase: {}", phrase);

    return this.messageFacade.getMessage(phrase);
  }


  @Resource
  public void setMessageFacade(MessageFacade messageFacade) {
    this.messageFacade = messageFacade;
  }
}
