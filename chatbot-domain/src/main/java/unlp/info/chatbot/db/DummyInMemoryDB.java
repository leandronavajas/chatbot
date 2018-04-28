package unlp.info.chatbot.db;

import org.springframework.stereotype.Repository;
import unlp.info.chatbot.dto.TestDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DummyInMemoryDB {

  private static Map<String, TestDTO> magicMap = new HashMap<>();

  public String save(TestDTO coupon) {
    magicMap.put(coupon.getId(), coupon);

    return "OK";
  }

  public TestDTO load(String id) {
    return magicMap.get(id);
  }

  public String remove(String id) {
    magicMap.remove(id);

    return "OK";
  }

  public List<TestDTO> loadAll() {
    return new ArrayList<>(magicMap.values());
  }

}
