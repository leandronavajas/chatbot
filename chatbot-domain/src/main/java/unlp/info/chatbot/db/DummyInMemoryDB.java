package unlp.info.chatbot.db;

import org.springframework.stereotype.Repository;
import unlp.info.chatbot.model.PersistentObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DummyInMemoryDB<P extends PersistentObject> {

  private Map<String, P> magicMap = new HashMap<>();

  public String save(P item) {
    magicMap.put(item.getId(), item);

    return "OK";
  }

  public P load(String id) {
    return magicMap.get(id);
  }

  public String remove(String id) {
    magicMap.remove(id);

    return "OK";
  }

  public List<P> loadAll() {
    return new ArrayList<>(magicMap.values());
  }

}
