package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.request.AddExpressionRequest;

@Component
public class ExpressionPersistentTransformer implements PersistentTransformer<AddExpressionRequest, EntityPersistent> {

  @Override
  public EntityPersistent transform(AddExpressionRequest in) {
    EntityPersistent entityPersistent = new EntityPersistent();

    entityPersistent.setId(in.getExpressionId());
    entityPersistent.setParentId(in.getItemId());
    entityPersistent.setDescription(in.getDescription());
    entityPersistent.setKind("EXPRESSION");
    entityPersistent.setWitId(in.getWitId());

    return entityPersistent;
  }
}
