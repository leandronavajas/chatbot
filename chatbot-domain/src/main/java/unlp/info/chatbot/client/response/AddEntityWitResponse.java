package unlp.info.chatbot.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddEntityWitResponse implements WitResponse {

  private String  id;
  private String name;
  private String lang;
  private Boolean builtin;
  private String doc;
  private List<String> lookups;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public Boolean getBuiltin() {
    return builtin;
  }

  public void setBuiltin(Boolean builtin) {
    this.builtin = builtin;
  }

  public String getDoc() {
    return doc;
  }

  public void setDoc(String doc) {
    this.doc = doc;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }

  public List<String> getLookups() {
    return lookups;
  }

  public void setLookups(List<String> lookups) {
    this.lookups = lookups;
  }
}
