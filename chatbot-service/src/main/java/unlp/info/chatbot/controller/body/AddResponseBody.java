package unlp.info.chatbot.controller.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import unlp.info.chatbot.dto.QuickReplyDTO;

import java.util.List;

public class AddResponseBody {

  private String entity;

  private String description;

  @JsonProperty("quick_replies")
  private List<QuickReplyDTO> quickReplies;

  private List<String> links;

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

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
