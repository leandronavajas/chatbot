package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddPhraseOperationRequest;

@Component
public class PhrasePersistentTransformer implements PersistentTransformer<AddPhraseOperationRequest, EntityPersistent> {

  @Override
  public EntityPersistent transform(AddPhraseOperationRequest in) {
    EntityPersistent entityPersistent = new EntityPersistent();

    entityPersistent.setId(in.getDescription());
    entityPersistent.setParentId(in.getEntity());
    entityPersistent.setKind("PHRASE");
    entityPersistent.setWitId(in.getWitId());

    return entityPersistent;

  }
}
