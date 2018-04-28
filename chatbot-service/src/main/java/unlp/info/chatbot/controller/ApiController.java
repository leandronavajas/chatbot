package unlp.info.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlp.info.chatbot.annotation.RequestTracking;
import unlp.info.chatbot.controller.body.AddItemBody;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.dto.TestDTO;
import unlp.info.chatbot.facade.ApiFacade;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("item")
public class ApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

  private ApiFacade apiFacade;

  @RequestTracking
  @GetMapping("/{id}")
  public TestDTO get(@PathVariable String id) {
    LOGGER.info("[GET] Get item with id: {}", id);

    return this.apiFacade.get(id);
  }

  @RequestTracking
  @PostMapping()
  public TestDTO add(@RequestBody AddItemBody body){
    LOGGER.info("[ADD] Add item with body: {}", body);

    return this.apiFacade.add(body);
  }

  @RequestTracking
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<StatusResponse> remove(@PathVariable String id) {
    LOGGER.info("[REMOVE] Remove item");

    return this.apiFacade.remove(id);
  }

  @RequestTracking
  @GetMapping()
  public List<TestDTO> getAll() {
    LOGGER.info("[GET_ALL] Get all items");

    return this.apiFacade.get();

  }

  @Resource
  public void setApiFacade(ApiFacade apiFacade) {
    this.apiFacade = apiFacade;
  }
}
