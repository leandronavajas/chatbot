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
@RequestMapping()
public class ApiEntityController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApiEntityController.class);

  private EntityFacade entityFacade;


  @RequestTracking
  @PostMapping("/category")
  public MessageDTO addCategory(@RequestBody AddEntityBody body) {
    LOGGER.info("Add Category: {}", body);

    return this.entityFacade.addCategory(body);
  }

  @RequestTracking
  @PostMapping("/category/{categoryId}/item")
  public MessageDTO addItem(@PathVariable String categoryId, @RequestBody AddEntityBody body) {
    LOGGER.info("Add Item for category: {} -> {}", categoryId, body);

    return this.entityFacade.addItem(categoryId, body);
  }

  @RequestTracking
  @PostMapping("/category/{categoryId}/item/{itemId}/expression")
  public MessageDTO addExpression(@PathVariable String categoryId, @PathVariable String itemId, @RequestBody AddEntityBody body) {
    LOGGER.info("Add Item for category: {} -> {}", categoryId, body);

    return this.entityFacade.addExpression(categoryId, itemId, body);
  }


  // DEPRECATED

  @Deprecated
  @RequestTracking
  @PostMapping
  public MessageDTO add(@RequestBody AddEntityBody body) {
    LOGGER.info("Add Message: {}", body);

    return this.entityFacade.addCategory(body);
  }

  @Deprecated
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
