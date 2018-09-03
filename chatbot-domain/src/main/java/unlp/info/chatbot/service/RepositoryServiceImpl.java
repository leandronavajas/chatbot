package unlp.info.chatbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import unlp.info.chatbot.db.DummyInMemoryDB;
import unlp.info.chatbot.exception.ItemNotFoundException;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.model.PersistentObject;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RepositoryServiceImpl<P extends PersistentObject> implements RepositoryService<P> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryServiceImpl.class);

  private DummyInMemoryDB<P> db;

  public void save(P item) {
    LOGGER.debug("Saving item -> {}", item);

    String status = this.db.save(item);

    LOGGER.info("Save item status: {}", status);
  }

  public List<P> getAll(String filter) {
    LOGGER.debug("Loading all items.");
    List<P> items = this.db.loadAll();
    LOGGER.debug("Number of items -> {}", items.size());

    return items;
  }

  @Override
  public List<EntityPersistent> getItems(String categoryId) {
    return null;
  }

  @Override
  public List<EntityPersistent> getSynonyms(String categoryId, String itemId) {
    return null;
  }

  @Override
  public List<EntityPersistent> getPhrases(String categoryId, String itemId) {
    return null;
  }

  public P getById(String id) {
    LOGGER.debug("Loading item by ID: {}", id);
    P item = this.db.load(id);

    if (item == null) {
      String causes = String.format("Item with id: %s Not found", id);
      LOGGER.error("[REPOSITORY SERVICE] {}", causes);
      throw new ItemNotFoundException(causes);
    }

    return item;
  }

  public void remove(String id) {

    P item = this.getById(id);

    LOGGER.debug("Removing item with id: {}", id);
    String status = this.db.remove(item.getId());

    LOGGER.info("Remove item status: {}", status);
  }

  @Resource
  public void setDb(DummyInMemoryDB<P> db) {
    this.db = db;
  }

}
