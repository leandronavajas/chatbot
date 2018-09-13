package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddSynonymOperationRequest;

import static unlp.info.chatbot.model.EntityKind.SYNONYM;

@Component
public class SynonymPersistentTransformer implements PersistentTransformer<AddSynonymOperationRequest, EntityPersistent> {

  @Override
  public EntityPersistent transform(AddSynonymOperationRequest in) {
    EntityPersistent entityPersistent = new EntityPersistent();

    entityPersistent.setId(in.getSynonymId());
    entityPersistent.setParentId(in.getItemId());
    entityPersistent.setDescription(in.getDescription());
    entityPersistent.setKind(SYNONYM);
    entityPersistent.setWitId(in.getWitId());

    return entityPersistent;
  }
}
