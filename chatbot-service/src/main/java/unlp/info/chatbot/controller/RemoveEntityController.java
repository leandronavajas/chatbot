package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.facade.RemoveEntityFacade;

import javax.annotation.Resource;

@RestController
@RequestMapping()
public class RemoveEntityController {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveEntityController.class);

  private RemoveEntityFacade removeEntityFacade;


  @RequestTracking
  @DeleteMapping("/category/{categoryId}")
  public StatusResponse removeCategory(@PathVariable String categoryId) {
    LOGGER.info("Remove category: {}", categoryId);
    return this.removeEntityFacade.removeCategory(categoryId);
  }

  @RequestTracking
  @DeleteMapping("/category/{categoryId}/item/{itemId}")
  public StatusResponse removeItem(@PathVariable String categoryId, @PathVariable String itemId) {
    LOGGER.info("Remove item: {} from category: {}", itemId, categoryId);
    return this.removeEntityFacade.removeItem(categoryId, itemId);
  }

  @RequestTracking
  @DeleteMapping("/category/{categoryId}/item/{itemId}/synonym/{synonymId}")
  public StatusResponse removeSynonym(@PathVariable String categoryId, @PathVariable String itemId, @PathVariable String synonymId) {
    LOGGER.info("Remove synonym: {} from item: {} in category: {}", synonymId, itemId, categoryId);
    return this.removeEntityFacade.removeSynonym(categoryId, itemId, synonymId);
  }

  @RequestTracking
  @DeleteMapping("/category/{categoryId}/item/{itemId}/phrase/{phrase}")
  public StatusResponse removePhrase(@PathVariable String categoryId, @PathVariable String itemId, @PathVariable String phrase) {
    LOGGER.info("Remove phrase: {} from item: {} in category: {}", phrase, itemId, categoryId);
    return this.removeEntityFacade.removePhrase(categoryId, itemId, phrase);
  }



  @Resource
  public void setRemoveEntityFacade(RemoveEntityFacade removeEntityFacade) {
    this.removeEntityFacade = removeEntityFacade;
  }
}
