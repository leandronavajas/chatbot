package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddSynonymPathAndBody;
import unlp.info.chatbot.operation.request.AddSynonymRequest;

@Component
public class AddSynonymRequestTransformer implements  RequestTransformer<AddSynonymPathAndBody, AddSynonymRequest> {

  @Override
  public AddSynonymRequest transform(AddSynonymPathAndBody in) {
    AddSynonymRequest addSynonymRequest = new AddSynonymRequest();

    addSynonymRequest.setEntity(in.getCategoryId());
    addSynonymRequest.setLinks(in.getBody().getLinks());
    addSynonymRequest.setItemId(in.getItemId());
    addSynonymRequest.setSynonymId(in.getBody().getDescription());

    return addSynonymRequest;
  }

}
