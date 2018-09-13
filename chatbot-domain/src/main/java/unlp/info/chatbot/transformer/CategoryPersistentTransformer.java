package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityKind;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddCategoryOperationRequest;

import static unlp.info.chatbot.model.EntityKind.CATEGORY;

@Component
public class CategoryPersistentTransformer implements PersistentTransformer<AddCategoryOperationRequest, EntityPersistent> {

  @Override
  public EntityPersistent transform(AddCategoryOperationRequest in) {
    EntityPersistent entityPersistent = new EntityPersistent();

    entityPersistent.setId(in.getEntity());
    entityPersistent.setDescription(in.getDescription());
    entityPersistent.setKind(CATEGORY);
    entityPersistent.setWitId(in.getWitId());
    entityPersistent.setLinks(in.getLinks());

    return entityPersistent;
  }
}
