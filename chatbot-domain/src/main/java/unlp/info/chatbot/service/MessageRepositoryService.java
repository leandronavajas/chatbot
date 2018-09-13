package unlp.info.chatbot.service;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Assignment;
import com.datastax.driver.core.querybuilder.Clause;
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

import static com.datastax.driver.core.querybuilder.QueryBuilder.*;
import static unlp.info.chatbot.model.EntityKind.ITEM;
import static unlp.info.chatbot.model.EntityKind.PHRASE;
import static unlp.info.chatbot.model.EntityKind.SYNONYM;
import static unlp.info.chatbot.service.constants.RepositoryConstants.*;

@Repository
public class MessageRepositoryService implements RepositoryService<EntityPersistent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageRepositoryService.class);

  private CassandraSessionService cassandraSessionService;


  @Override
  public void save(EntityPersistent item) {
    Session session = this.cassandraSessionService.cassandraSession();
    Statement addStatement = insertInto(KEYSPACE, TABLE)

        .value(ID, item.getId())
        .value(DESCRIPTION, item.getDescription())
        .value(LINKS, item.getLinks())
        .value(KIND, item.getKind())
        .value(PARENT_ID, item.getParentId())
        .value(WIT_ID, item.getWitId());
    session.execute(addStatement);

    LOGGER.info("[MESSAGE REPOSITORY SERVICE] Item saved");
  }

  @Override
  public List<EntityPersistent> getAll(String filterField, String filterValue) {
    LOGGER.info("Get all items with filter_field: {} and filter_value: {}", filterField, filterValue);
    Session session = this.cassandraSessionService.cassandraSession();

    Statement statement = select().all().from(KEYSPACE, TABLE).allowFiltering().where(eq(filterField, filterValue));

    ResultSet resultSet = session.execute(statement);

    List<Row> rows = resultSet.all();

    if (CollectionUtils.isEmpty(rows)) {
      throw new ItemNotFoundException("Get all response without items. Filter -> field: " + filterField + " - value: " + filterValue + "");
    }

    return this.mappingAll(rows);
  }

  @Override
  public List<EntityPersistent> getAllSafe(String filterField, String filterValue) {
    LOGGER.info("Get all items with filter_field: {} and filter_value: {}", filterField, filterValue);
    Session session = this.cassandraSessionService.cassandraSession();

    Statement statement = select().all().from(KEYSPACE, TABLE).allowFiltering().where(eq(filterField, filterValue));

    ResultSet resultSet = session.execute(statement);

    List<Row> rows = resultSet.all();

    if (CollectionUtils.isEmpty(rows)) {
      return Lists.newArrayList();
    }

    return this.mappingAll(rows);
  }

  public List<EntityPersistent> getItems(String categoryId) {
    LOGGER.info("Get items for category: {}", categoryId);
    Session session = this.cassandraSessionService.cassandraSession();

    Statement statement =
        select().all()
            .from(KEYSPACE, TABLE).allowFiltering()
            .where(eq(PARENT_ID, categoryId))
            .and(eq(KIND, ITEM));

    ResultSet resultSet = session.execute(statement);

    List<Row> rows = resultSet.all();

    if (CollectionUtils.isEmpty(rows)) {
      throw new ItemNotFoundException("Get items for category. Category_id: " + categoryId + "");
    }

    return this.mappingAll(rows);
  }

  public List<EntityPersistent> getSynonyms(String categoryId, String itemId) {
    LOGGER.info("Get synonyms for item: {} in category: {}", itemId, categoryId);
    return this.getExpressions(categoryId, itemId, SYNONYM);
  }

  public List<EntityPersistent> getPhrases(String categoryId, String itemId) {
    LOGGER.info("Get phrases for item: {} in category: {}", itemId, categoryId);
    return this.getExpressions(categoryId, itemId, PHRASE);
  }

  private List<EntityPersistent> getExpressions(String categoryId, String itemId, String kind) {
    Session session = this.cassandraSessionService.cassandraSession();

    Statement statement =
        select().all()
            .from(KEYSPACE, TABLE).allowFiltering()
            .where(eq(PARENT_ID, itemId))
            .and(eq(KIND, kind));

    ResultSet resultSet = session.execute(statement);

    List<Row> rows = resultSet.all();

    if (CollectionUtils.isEmpty(rows)) {
      throw new ItemNotFoundException("Get synonyms for item. Category_id: " + categoryId + " - Item_id: " + itemId);
    }

    return this.mappingAll(rows);
  }

  @Override
  public EntityPersistent getById(String id) {
    LOGGER.info("Get by id: {}", id);
    Session session = this.cassandraSessionService.cassandraSession();
    Statement statement = select().all().from(KEYSPACE, TABLE).where(eq(ID, id)).limit(1);
    ResultSet resultSet = session.execute(statement);

    Row row = resultSet.one();

    if (row == null) {
      throw new ItemNotFoundException("Id: '" + id + "' not found in DB");
    }

    return this.mapping(row);
  }

  @Override
  public void remove(String witId) {
    LOGGER.info("Remove entity by id: {}", witId);
    List<EntityPersistent> entitiesToRemove = this.getAll(WIT_ID, witId);

    for (EntityPersistent entityToRemove : entitiesToRemove) {
      Session session = this.cassandraSessionService.cassandraSession();
      Statement statement = delete().from(KEYSPACE, TABLE).where(eq(ID, entityToRemove.getId()));
      session.execute(statement);
    }

  }

  @Override
  public void remove(EntityPersistent entityPersistent) {
    LOGGER.info("Remove entity: {}", entityPersistent);

    Session session = this.cassandraSessionService.cassandraSession();
    Statement statement = delete().from(KEYSPACE, TABLE).where(eq(ID, entityPersistent.getId()));
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
    String itemId = row.getString(ID);
    String itemDescription = row.getString(DESCRIPTION);
    List<String> links = row.getList(LINKS, String.class);
    String kind = row.getString(KIND);
    String parentId = row.getString(PARENT_ID);
    String witId = row.getString(WIT_ID);

    EntityPersistent entityPersistent = new EntityPersistent();
    entityPersistent.setId(itemId);
    entityPersistent.setDescription(itemDescription);
    entityPersistent.setLinks(links);
    entityPersistent.setKind(kind);
    entityPersistent.setParentId(parentId);
    entityPersistent.setWitId(witId);

    return entityPersistent;
  }

  @Resource
  public void setCassandraSessionService(CassandraSessionService cassandraSessionService) {
    this.cassandraSessionService = cassandraSessionService;
  }

}
