package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.facade.EntityFacade;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping()
public class GetEntityController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetEntityController.class);

  private EntityFacade entityFacade;

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
  @GetMapping("/category/{categoryId}/item/{itemId}/synonyms")
  public List<MessageDTO> getSynonymsForItem(@PathVariable String categoryId, @PathVariable String itemId) {
    LOGGER.info("Get synonyms for item. Category_id: {} - Item_id: {}", categoryId, itemId);

    return this.entityFacade.getSynonymsForItem(categoryId, itemId);
  }


  @Resource
  public void setEntityFacade(EntityFacade entityFacade) {
    this.entityFacade = entityFacade;
  }
}
