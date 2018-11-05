package unlp.info.chatbot.transformer;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.Entity;
import unlp.info.chatbot.model.EntityKind;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.GetItemsForCategoryRequest;

import javax.annotation.Resource;
import java.util.List;

@Component
public class EntityTransformer implements ModelTransformer<EntityPersistent, Entity> {

  private Operation<GetItemsForCategoryRequest, List<Entity>> getItemsForCategoryOperation;

  @Override
  public Entity transform(EntityPersistent entityPersistent) {
    Entity entity = new Entity();

    entity.setId(entityPersistent.getId());
    entity.setDescription(entityPersistent.getDescription());
    entity.setKind(entityPersistent.getKind());
    entity.setLinks(entityPersistent.getLinks());
    entity.setParentId(entityPersistent.getParentId());
    entity.setWitId(entityPersistent.getWitId());

    if (EntityKind.CATEGORY.equalsIgnoreCase(entityPersistent.getKind())) {
      GetItemsForCategoryRequest request = new GetItemsForCategoryRequest(entity.getId());
      List<Entity> childrenPersistent = this.getItemsForCategoryOperation.execute(request);

      List<String> children = Lists.newArrayList();
      for (Entity child : childrenPersistent) {
        children.add(child.getId());
      }
      entity.setChildren(children);
    }

    return entity;
  }

  @Resource
  public void setGetItemsForCategoryOperation(Operation<GetItemsForCategoryRequest, List<Entity>> getItemsForCategoryOperation) {
    this.getItemsForCategoryOperation = getItemsForCategoryOperation;
  }

}
