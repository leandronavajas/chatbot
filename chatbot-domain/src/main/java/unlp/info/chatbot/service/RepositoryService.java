package unlp.info.chatbot.service;


import unlp.info.chatbot.model.PersistentObject;

import java.util.List;

public interface RepositoryService<P extends PersistentObject> {

  void save(P item);

  List<P> getAll();

  P getById(String id);

  void remove(String id);


}
