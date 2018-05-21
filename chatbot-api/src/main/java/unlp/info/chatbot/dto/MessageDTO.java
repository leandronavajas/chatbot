package unlp.info.chatbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import unlp.info.chatbot.model.PersistentObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageDTO implements PersistentObject, Serializable {

  // TODO: LN ver si con la serializacion puedo guardar el objeto entero
  private static final long serialVersionUID = 1L;

  private String entity;

  private String description;

  private List<MessageDTO> siblings = new ArrayList<>();

  @JsonProperty("quick_reply")
  private QuickReplyDTO quickReply;

  private List<String> links;

  @Override
  public String getId() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
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

  public List<MessageDTO> getSiblings() {
    return siblings;
  }

  public void setSiblings(List<MessageDTO> siblings) {
    this.siblings = siblings;
  }

  public QuickReplyDTO getQuickReply() {
    return quickReply;
  }

  public void setQuickReply(QuickReplyDTO quickReply) {
    this.quickReply = quickReply;
  }
}
