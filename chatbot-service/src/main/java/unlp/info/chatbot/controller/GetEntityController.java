package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.facade.GetEntityFacade;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping()
public class GetEntityController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetEntityController.class);

  private GetEntityFacade getEntityFacade;

  @RequestTracking
  @GetMapping()
  public List<MessageDTO> getAll(@RequestParam String filter) {
    LOGGER.info("Get all entities. Filter: {}", filter);

    return this.getEntityFacade.getAll(filter);
  }

  @RequestTracking
  @GetMapping("/category/{categoryId}")
  public List<MessageDTO> getItemsForCategory(@PathVariable String categoryId) {
    LOGGER.info("Get items for category. Category_id: {}", categoryId);

    return this.getEntityFacade.getItemsForCategory(categoryId);
  }

  @RequestTracking
  @GetMapping("/category/{categoryId}/item/{itemId}/synonyms")
  public List<MessageDTO> getSynonymsForItem(@PathVariable String categoryId, @PathVariable String itemId) {
    LOGGER.info("Get synonyms for item. Category_id: {} - Item_id: {}", categoryId, itemId);

    return this.getEntityFacade.getSynonymsForItem(categoryId, itemId);
  }

  @RequestTracking
  @GetMapping("/category/{categoryId}/item/{itemId}/phrases")
  public List<MessageDTO> getPhrasesForItem(@PathVariable String categoryId, @PathVariable String itemId) {
    LOGGER.info("Get phrases for item. Category_id: {} - Item_id: {}", categoryId, itemId);

    return this.getEntityFacade.getPhrasesForItem(categoryId, itemId);
  }


  @Resource
  public void setGetEntityFacade(GetEntityFacade getEntityFacade) {
    this.getEntityFacade = getEntityFacade;
  }
}
