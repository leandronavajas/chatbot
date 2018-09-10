package unlp.info.chatbot.service;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Assignment;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import unlp.info.chatbot.db.CassandraSessionService;
import unlp.info.chatbot.exception.ItemNotFoundException;
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
  public List<EntityPersistent> getAll(String filter) {
    LOGGER.info("Get all items with filter: {}", filter);
    Session session = this.cassandraSessionService.cassandraSession();

    Statement statement =QueryBuilder.select().all().from(KEYSPACE, TABLE).allowFiltering().where(QueryBuilder.eq("kind", filter));

    ResultSet resultSet = session.execute(statement);

    List<Row> rows = resultSet.all();

    if (CollectionUtils.isEmpty(rows)) {
      throw new ItemNotFoundException("[MANAGE REPOSITORY SERVICE] Get all response without items. Filter: " + filter + "");
    }

    return this.mappingAll(rows);

  }

  public List<EntityPersistent> getItems(String categoryId) {
    LOGGER.info("Get items for category: {}", categoryId);
    Session session = this.cassandraSessionService.cassandraSession();

    Statement statement = QueryBuilder
        .select().all()
        .from(KEYSPACE, TABLE).allowFiltering()
        .where(QueryBuilder.eq("parentid", categoryId))
        .and(QueryBuilder.eq("kind", "ITEM"));

    ResultSet resultSet = session.execute(statement);

    List<Row> rows = resultSet.all();

    if (CollectionUtils.isEmpty(rows)) {
      throw new ItemNotFoundException("[MANAGE REPOSITORY SERVICE] Get items for category. Category_id: " + categoryId + "");
    }

    return this.mappingAll(rows);
  }

  public List<EntityPersistent> getSynonyms(String categoryId, String itemId) {
    LOGGER.info("Get synonyms for item: {} in category: {}", itemId, categoryId);
    return this.getExpressions(categoryId, itemId, "SYNONYM");
  }

  public List<EntityPersistent> getPhrases(String categoryId, String itemId) {
    LOGGER.info("Get phrases for item: {} in category: {}", itemId, categoryId);
    return this.getExpressions(categoryId, itemId, "PHRASE");
  }

  private List<EntityPersistent> getExpressions(String categoryId, String itemId, String kind) {
    Session session = this.cassandraSessionService.cassandraSession();

    Statement statement = QueryBuilder
        .select().all()
        .from(KEYSPACE, TABLE).allowFiltering()
        .where(QueryBuilder.eq("parentid", itemId))
        .and(QueryBuilder.eq("kind", kind));

    ResultSet resultSet = session.execute(statement);

    List<Row> rows = resultSet.all();

    if (CollectionUtils.isEmpty(rows)) {
      throw new ItemNotFoundException("[MANAGE REPOSITORY SERVICE] Get synonyms for item. Category_id: " + categoryId + " - Item_id: " + itemId);
    }

    return this.mappingAll(rows);
  }

  @Override
  public EntityPersistent getById(String id) {
    LOGGER.info("Get by id: {}", id);
    Session session = this.cassandraSessionService.cassandraSession();
    Statement statement = QueryBuilder.select().all().from(KEYSPACE, TABLE).where(QueryBuilder.eq("id", id)).limit(1);
    ResultSet resultSet = session.execute(statement);

    Row row = resultSet.one();

    if (row == null) {
      throw new ItemNotFoundException("[MANAGE REPOSITORY SERVICE] Id: '" + id + "' not found in DB");
    }

    return this.mapping(row);
  }

  @Override
  public void remove(String id) {
    LOGGER.info("Remove entity by id: {}", id);
    Session session = this.cassandraSessionService.cassandraSession();

    Statement statement = QueryBuilder.delete().from(KEYSPACE, TABLE).where(QueryBuilder.eq("id", id));
    session.execute(statement);
  }

  // PRIVATE METHODS

  private List<EntityPersistent> mappingAll(List<Row> rows) {
    List<EntityPersistent> response = Lists.newArrayList();

    for (Row row : rows) {
      EntityPersistent entityPersistent = this.mapping(row);
      response.add(entityPersistent);
    }
    return response;

  }

  private EntityPersistent mapping(Row row) {
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

  @Resource
  public void setCassandraSessionService(CassandraSessionService cassandraSessionService) {
    this.cassandraSessionService = cassandraSessionService;
  }

}
