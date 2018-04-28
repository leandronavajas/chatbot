package unlp.info.chatbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import unlp.info.chatbot.db.DummyInMemoryDB;
import unlp.info.chatbot.dto.TestDTO;
import unlp.info.chatbot.exception.ItemNotFoundException;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RepositoryService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryService.class);

  private DummyInMemoryDB db;

  public void save(TestDTO item) {
    LOGGER.debug("Saving item -> {}", item);

    String status = this.db.save(item);

    LOGGER.info("Save item status: {}", status);
  }

  public List<TestDTO> getAll() {
    LOGGER.debug("Loading all items.");
    List<TestDTO> coupons = this.db.loadAll();
    LOGGER.debug("Number of items -> {}", coupons.size());

    return coupons;
  }

  public TestDTO getById(String id) {
    LOGGER.debug("Loading item by ID: {}", id);
    TestDTO coupon = this.db.load(id);

    if (coupon == null) {
      String causes = String.format("Coupon with id: %s Not found", id);
      LOGGER.error("[REPOSITORY SERVICE] {}", causes);
      throw new ItemNotFoundException(causes);
    }

    return coupon;
  }

  public void remove(String id) {

    TestDTO item = this.getById(id);

    LOGGER.debug("Removing item with id: {}", id);
    String status = this.db.remove(item.getId());

    LOGGER.info("Remove item status: {}", status);
  }

  @Resource
  public void setDb(DummyInMemoryDB db) {
    this.db = db;
  }

}
