package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.client.request.AddValueEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.facade.EntityFacade;

import javax.annotation.Resource;

@RestController
@RequestMapping("entity")
public class ApiEntityController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApiEntityController.class);

  private EntityFacade entityFacade;

  @RequestTracking
  @PostMapping
  public MessageDTO add(@RequestBody AddEntityBody body) {
    LOGGER.info("Add Message: {}", body);

    return this.entityFacade.addEntity(body);
  }


  @RequestTracking
  @PostMapping("/{entity}/value")
  public AddEntityWitResponse addValue(@PathVariable String entity, @RequestBody AddValueEntityWitRequest body) {
    return this.entityFacade.addValueForEntity(entity, body);
  }


  @Resource
  public void setEntityFacade(EntityFacade entityFacade) {
    this.entityFacade = entityFacade;
  }
}
