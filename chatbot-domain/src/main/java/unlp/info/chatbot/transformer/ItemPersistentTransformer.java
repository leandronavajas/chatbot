package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddItemOperationRequest;

import static unlp.info.chatbot.model.EntityKind.ITEM;

@Component
public class ItemPersistentTransformer implements PersistentTransformer<AddItemOperationRequest, EntityPersistent> {

  @Override
  public EntityPersistent transform(AddItemOperationRequest in) {
    EntityPersistent entityPersistent = new EntityPersistent();

    entityPersistent.setId(in.getItemId());
    entityPersistent.setParentId(in.getEntity());
    entityPersistent.setDescription(in.getDescription());
    entityPersistent.setKind(ITEM);
    entityPersistent.setWitId(in.getWitId());
    entityPersistent.setLinks(in.getLinks());

    return entityPersistent;

  }
}
