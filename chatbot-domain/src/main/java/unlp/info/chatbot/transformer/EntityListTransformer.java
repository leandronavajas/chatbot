package unlp.info.chatbot.transformer;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.model.Entity;
import unlp.info.chatbot.model.EntityPersistent;

import javax.annotation.Resource;
import java.util.List;

@Component
public class EntityListTransformer implements ModelTransformer<List<EntityPersistent>, List<Entity>> {

  private ModelTransformer<EntityPersistent, Entity> entityTransformer;

  @Override
  public List<Entity> transform(List<EntityPersistent> in) {
    List<Entity> out = Lists.newArrayList();

    for (EntityPersistent entityPersistent : in) {
      Entity entity = this.entityTransformer.transform(entityPersistent);
      out.add(entity);
    }

    return out;
  }

  @Resource
  public void setEntityTransformer(ModelTransformer<EntityPersistent, Entity> entityTransformer) {
    this.entityTransformer = entityTransformer;
  }
}
