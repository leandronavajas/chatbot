package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddSynonymRequest;

@Component
public class SynonymPersistentTransformer implements PersistentTransformer<AddSynonymRequest, EntityPersistent> {

  @Override
  public EntityPersistent transform(AddSynonymRequest in) {
    EntityPersistent entityPersistent = new EntityPersistent();

    entityPersistent.setId(in.getSynonymId());
    entityPersistent.setParentId(in.getItemId());
    entityPersistent.setDescription(in.getDescription());
    entityPersistent.setKind("SYNONYM");
    entityPersistent.setWitId(in.getWitId());

    return entityPersistent;
  }
}
