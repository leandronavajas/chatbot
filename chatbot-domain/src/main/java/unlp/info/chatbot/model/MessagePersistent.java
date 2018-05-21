package unlp.info.chatbot.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table("message_v1")
public class MessagePersistent implements PersistentObject, Serializable {

  private static final long serialVersionUID = 1L;

  @PrimaryKey
  private String id;

  private String description;

  private List<String> siblings = new ArrayList<>();

  private List<String> quickReply;

  private List<String> links;

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

  public List<String> getSiblings() {
    return siblings;
  }

  public void setSiblings(List<String> siblings) {
    this.siblings = siblings;
  }

  public List<String> getQuickReply() {
    return quickReply;
  }

  public void setQuickReply(List<String> quickReply) {
    this.quickReply = quickReply;
  }

  public List<String> getLinks() {
    return links;
  }

  public void setLinks(List<String> links) {
    this.links = links;
  }
}
