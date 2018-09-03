package unlp.info.chatbot.operation.request;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class AddCategoryOperationRequest implements AddEntityRequest {

  private String entity;

  private String description;

  private List<String> links;

  private String witId;

  private String parentId;

  // GETTER and SETTERS

  @Override
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

  public String getWitId() {
    return witId;
  }

  public void setWitId(String witId) {
    this.witId = witId;
  }
}
