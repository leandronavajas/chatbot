package unlp.info.chatbot.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import unlp.info.chatbot.service.constants.RepositoryConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static unlp.info.chatbot.service.constants.RepositoryConstants.TABLE;

@Table(TABLE)
public class EntityPersistent implements PersistentObject, Serializable {

  private static final long serialVersionUID = 1L;

  @PrimaryKey
  private String id;
  private String description;
  private List<String> links;
  private String kind;
  private String parentId;
  private String witId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getLinks() {
    return links;
  }

  public void setLinks(List<String> links) {
    this.links = links;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getWitId() {
    return witId;
  }

  public void setWitId(String witId) {
    this.witId = witId;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
