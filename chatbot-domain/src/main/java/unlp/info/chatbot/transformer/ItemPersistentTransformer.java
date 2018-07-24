package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddItemRequest;

@Component
public class ItemPersistentTransformer implements PersistentTransformer<AddItemRequest, EntityPersistent> {

  @Override
  public EntityPersistent transform(AddItemRequest in) {
    EntityPersistent entityPersistent = new EntityPersistent();

    entityPersistent.setId(in.getItemId());
    entityPersistent.setParentId(in.getEntity());
    entityPersistent.setDescription(in.getDescription());
    entityPersistent.setKind("ITEM");
    entityPersistent.setWitId(in.getWitId());
    entityPersistent.setLinks(in.getLinks());

    return entityPersistent;

  }
}
