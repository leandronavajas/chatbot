package unlp.info.chatbot.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WitMessageEntityResponse implements WitResponse {

  // TODO: LN chequear por que no levanta el mappeo del objeto, t
  private Map<String, List<WitMessageMetricResponse>> metric;

  public Map<String, List<WitMessageMetricResponse>> getMetric() {
    return metric;
  }

  public void setMetric(Map<String, List<WitMessageMetricResponse>> metric) {
    this.metric = metric;
  }
}
