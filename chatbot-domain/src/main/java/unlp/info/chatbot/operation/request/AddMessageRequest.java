package unlp.info.chatbot.operation.request;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import unlp.info.chatbot.dto.QuickReplyDTO;

import java.util.List;

public class AddMessageRequest {

  private String entity;

  private String description;

  private QuickReplyDTO quickReply;

  private List<String> links;

  private String parentId;

  // GETTER and SETTERS

  public String getEntity() {
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

  public QuickReplyDTO getQuickReply() {
    return quickReply;
  }

  public void setQuickReply(QuickReplyDTO quickReply) {
    this.quickReply = quickReply;
  }

  public List<String> getLinks() {
    return links;
  }

  public void setLinks(List<String> links) {
    this.links = links;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }
}
