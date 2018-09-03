package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.facade.EntityFacade;

import javax.annotation.Resource;
import java.util.List;

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
  @PostMapping("/category/{categoryId}/item/{itemId}/synonym")
  public MessageDTO addSynonym(@PathVariable String categoryId, @PathVariable String itemId, @RequestBody AddEntityBody body) {
    LOGGER.info("Add Synonym: {} for item: {} in category: {}", body, itemId, categoryId);

    return this.entityFacade.addSynonym(categoryId, itemId, body);
  }

  @RequestTracking
  @PostMapping("/category/{categoryId}/item/{itemId}/phrase")
  public MessageDTO addPhrase(@PathVariable String categoryId, @PathVariable String itemId, @RequestBody AddEntityBody body) {
    LOGGER.info("Add phrase: {} -> for item: {} in category: {} ", body, itemId, categoryId);

    return this.entityFacade.addPhrase(categoryId, itemId, body);
  }


  @RequestTracking
  @GetMapping()
  public List<MessageDTO> getAll(@RequestParam String filter) {
    LOGGER.info("Get all entities. Filter: {}", filter);

    return this.entityFacade.getAll(filter);
  }

  @RequestTracking
  @GetMapping("/category/{categoryId}")
  public List<MessageDTO> getItemsForCategory(@PathVariable String categoryId) {
    LOGGER.info("Get items for category. Category_id: {}", categoryId);

    return this.entityFacade.getItemsForCategory(categoryId);
  }

  @RequestTracking
  @GetMapping("/category/{categoryId}/item/{itemId}")
  public List<MessageDTO> getSynonymsForItem(@PathVariable String categoryId, @PathVariable String itemId) {
    LOGGER.info("Get synonyms for item. Category_id: {} - Item_id: {}", categoryId, itemId);

    return this.entityFacade.getSynonymsForItem(categoryId, itemId);
  }


  @Resource
  public void setEntityFacade(EntityFacade entityFacade) {
    this.entityFacade = entityFacade;
  }
}
