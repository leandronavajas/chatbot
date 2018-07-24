package unlp.info.chatbot.service;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import unlp.info.chatbot.db.CassandraSessionService;
import unlp.info.chatbot.model.EntityPersistent;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class MessageRepositoryService implements RepositoryService<EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageRepositoryService.class);

  private static final String KEYSPACE = "chatbot";
  private static final String TABLE = "message_v3";

  private CassandraSessionService cassandraSessionService;


  @Override
  public void save(EntityPersistent item) {
    Session session = this.cassandraSessionService.cassandraSession();
    Statement addStatement = QueryBuilder.insertInto(KEYSPACE, TABLE)

        .value("id", item.getId())
        .value("description", item.getDescription())
        .value("links", item.getLinks())
        .value("kind", item.getKind())
        .value("parentid", item.getParentId())
        .value("quickreplies", item.getQuickReply())
        .value("witid", item.getWitId());
    session.execute(addStatement);

    LOGGER.info("[MESSAGE REPOSITORY SERVICE] Item saved");
  }

  @Override
  public List<EntityPersistent> getAll() {
    return null;
  }

  @Override
  public EntityPersistent getById(String id) {
    Session session = this.cassandraSessionService.cassandraSession();
    Statement statement = QueryBuilder.select().all().from(KEYSPACE, TABLE).where(QueryBuilder.eq("id", id)).limit(1);
    ResultSet resultSet = session.execute(statement);

    Row row = resultSet.one();

    String itemId = row.getString("id");
    String itemDescription = row.getString("description");
    List<String> links = row.getList("links", String.class);
    String kind = row.getString("kind");
    String parentId = row.getString("parentid");
    List<String> quickReplies = row.getList("quickreplies", String.class);
    String witId = row.getString("witid");

    EntityPersistent entityPersistent = new EntityPersistent();
    entityPersistent.setId(itemId);
    entityPersistent.setDescription(itemDescription);
    entityPersistent.setLinks(links);
    entityPersistent.setKind(kind);
    entityPersistent.setParentId(parentId);
    entityPersistent.setQuickReply(quickReplies);
    entityPersistent.setWitId(witId);

    return entityPersistent;
  }

  @Override
  public void remove(String id) {

  }

  @Resource
  public void setCassandraSessionService(CassandraSessionService cassandraSessionService) {
    this.cassandraSessionService = cassandraSessionService;
  }

}
