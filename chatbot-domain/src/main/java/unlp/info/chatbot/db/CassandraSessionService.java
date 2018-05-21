package unlp.info.chatbot.db;

import com.datastax.driver.core.Session;

public interface CassandraSessionService {

  Session cassandraSession();

}
