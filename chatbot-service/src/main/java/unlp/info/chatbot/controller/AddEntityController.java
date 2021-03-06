package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.facade.AddEntityFacade;

import javax.annotation.Resource;

@RestController
@RequestMapping()
public class AddEntityController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddEntityController.class);

  private AddEntityFacade addEntityFacade;

  @RequestTracking
  @PostMapping("/category")
  public MessageDTO addCategory(@RequestBody AddEntityBody body) {
    LOGGER.info("Add Category: {}", body);

    return this.addEntityFacade.addCategory(body);
  }

  @RequestTracking
  @PostMapping("/category/{categoryId}/item")
  public MessageDTO addItem(@PathVariable String categoryId, @RequestBody AddEntityBody body) {
    LOGGER.info("Add Item for category: {} -> {}", categoryId, body);

    return this.addEntityFacade.addItem(categoryId, body);
  }

  @RequestTracking
  @PostMapping("/category/{categoryId}/item/{itemId}/synonym")
  public MessageDTO addSynonym(@PathVariable String categoryId, @PathVariable String itemId, @RequestBody AddEntityBody body) {
    LOGGER.info("Add Synonym: {} for item: {} in category: {}", body, itemId, categoryId);

    return this.addEntityFacade.addSynonym(categoryId, itemId, body);
  }

  @RequestTracking
  @PostMapping("/category/{categoryId}/item/{itemId}/phrase")
  public MessageDTO addPhrase(@PathVariable String categoryId, @PathVariable String itemId, @RequestBody AddEntityBody body) {
    LOGGER.info("Add phrase: {} -> for item: {} in category: {} ", body, itemId, categoryId);

    return this.addEntityFacade.addPhrase(categoryId, itemId, body);
  }

  @Resource
  public void setAddEntityFacade(AddEntityFacade addEntityFacade) {
    this.addEntityFacade = addEntityFacade;
  }
}
