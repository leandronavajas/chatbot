package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddCategoryRequest;

@Component
public class CategoryPersistentTransformer implements PersistentTransformer<AddCategoryRequest, EntityPersistent> {

  @Override
  public EntityPersistent transform(AddCategoryRequest in) {
    EntityPersistent entityPersistent = new EntityPersistent();

    entityPersistent.setId(in.getEntity());
    entityPersistent.setDescription(in.getDescription());
    entityPersistent.setKind("CATEGORY");
    entityPersistent.setWitId(in.getWitId());
    entityPersistent.setLinks(in.getLinks());

    return entityPersistent;
  }
}
