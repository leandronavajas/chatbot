package unlp.info.chatbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseDTO implements PersistentObject {

  private String entity;

  private String description;

  @JsonProperty("quick_replies")
  private List<QuickReplyDTO> quickReplies;

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

  public List<QuickReplyDTO> getQuickReplies() {
    return quickReplies;
  }

  public void setQuickReplies(List<QuickReplyDTO> quickReplies) {
    this.quickReplies = quickReplies;
  }

  public List<String> getLinks() {
    return links;
  }

  public void setLinks(List<String> links) {
    this.links = links;
  }
}
