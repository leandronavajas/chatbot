package unlp.info.chatbot.wrapper;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.exception.InternalApiException;
import unlp.info.chatbot.model.EntityKind;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.RemoveEntitiesFromDBOperationRequest;

import javax.annotation.Resource;

import static unlp.info.chatbot.model.EntityKind.*;

@Component
public class RemoveEntityFromDBWrapper implements Operation<RemoveEntitiesFromDBOperationRequest, String> {

  private Operation<RemoveEntitiesFromDBOperationRequest, String> removeCategoryFromDBOperation;
  private Operation<RemoveEntitiesFromDBOperationRequest, String> removeItemFromDBOperation;
  private Operation<RemoveEntitiesFromDBOperationRequest, String> removeExpressionFromDBOperation;
  @Override
  public String execute(RemoveEntitiesFromDBOperationRequest request) {
    EntityPersistent entityPersistent = request.getEntityPersistent();
    String kind = entityPersistent.getKind();

    if (CATEGORY.equalsIgnoreCase(kind)) {
      return this.removeCategoryFromDBOperation.execute(request);
    }

    if (ITEM.equalsIgnoreCase(kind)) {
      return this.removeItemFromDBOperation.execute(request);
    }

    if (SYNONYM.equalsIgnoreCase(kind) || PHRASE.equalsIgnoreCase(kind)) {
      return this.removeExpressionFromDBOperation.execute(request);
    }

    throw new InternalApiException("Cannot determine Entity kind to remove. Entity: " + entityPersistent);
  }

  @Resource
  public void setRemoveCategoryFromDBOperation(Operation<RemoveEntitiesFromDBOperationRequest, String> removeCategoryFromDBOperation) {
    this.removeCategoryFromDBOperation = removeCategoryFromDBOperation;
  }

  @Resource
  public void setRemoveItemFromDBOperation(Operation<RemoveEntitiesFromDBOperationRequest, String> removeItemFromDBOperation) {
    this.removeItemFromDBOperation = removeItemFromDBOperation;
  }

  @Resource
  public void setRemoveExpressionFromDBOperation(Operation<RemoveEntitiesFromDBOperationRequest, String> removeExpressionFromDBOperation) {
    this.removeExpressionFromDBOperation = removeExpressionFromDBOperation;
  }
}
