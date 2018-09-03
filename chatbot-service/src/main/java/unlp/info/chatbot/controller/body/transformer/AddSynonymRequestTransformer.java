package unlp.info.chatbot.controller.body.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddSynonymPathAndBody;
import unlp.info.chatbot.operation.request.AddSynonymOperationRequest;

@Component
public class AddSynonymRequestTransformer implements  RequestTransformer<AddSynonymPathAndBody, AddSynonymOperationRequest> {

  @Override
  public AddSynonymOperationRequest transform(AddSynonymPathAndBody in) {
    AddSynonymOperationRequest addSynonymOperationRequest = new AddSynonymOperationRequest();

    addSynonymOperationRequest.setEntity(in.getCategoryId());
    addSynonymOperationRequest.setLinks(in.getBody().getLinks());
    addSynonymOperationRequest.setItemId(in.getItemId());
    addSynonymOperationRequest.setSynonymId(in.getBody().getDescription());

    return addSynonymOperationRequest;
  }

}
