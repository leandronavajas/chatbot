package unlp.info.chatbot.service;


import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.model.PersistentObject;

import java.util.List;

public interface RepositoryService<P extends PersistentObject> {

  void save(P item);

  List<P> getAll(String filterField, String filterValue);

  List<EntityPersistent> getAllSafe(String filterField, String filterValue);

  List<EntityPersistent> getItems(String categoryId);

  List<EntityPersistent> getSynonyms(String categoryId, String itemId);

  List<EntityPersistent> getPhrases(String categoryId, String itemId);

  P getById(String id);

  void remove(String witId);

  void remove(EntityPersistent entitiesToRemove);


}
