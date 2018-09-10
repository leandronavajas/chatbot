package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.controller.body.AddItemPathAndBody;
import unlp.info.chatbot.controller.body.AddSynonymPathAndBody;
import unlp.info.chatbot.controller.body.transformer.RequestTransformer;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.facade.AddEntityFacade;
import unlp.info.chatbot.model.EntityPersistent;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.AddCategoryOperationRequest;
import unlp.info.chatbot.operation.request.AddItemOperationRequest;
import unlp.info.chatbot.operation.request.AddPhraseOperationRequest;
import unlp.info.chatbot.operation.request.AddSynonymOperationRequest;

import javax.annotation.Resource;

@Component
public class AddEntityFacadeImpl implements AddEntityFacade {

  private RequestTransformer<AddEntityBody, AddCategoryOperationRequest> addCategoryRequestTransformer;
  private RequestTransformer<AddItemPathAndBody, AddItemOperationRequest> addItemRequestTransformer;
  private RequestTransformer<AddSynonymPathAndBody, AddSynonymOperationRequest> addSynonymRequestTransformer;

  private Operation<AddCategoryOperationRequest, EntityPersistent> addCategoryOperation;
  private Operation<AddItemOperationRequest, EntityPersistent> addItemOperation;
  private Operation<AddSynonymOperationRequest, EntityPersistent> addSynonymOperation;
  private Operation<AddPhraseOperationRequest, EntityPersistent> addPhraseOperation;

  private DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer;

  @Override
  public MessageDTO addCategory(AddEntityBody body) {

    AddCategoryOperationRequest addCategoryOperationRequest = this.addCategoryRequestTransformer.transform(body);

    EntityPersistent entityPersistent = this.addCategoryOperation.execute(addCategoryOperationRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public MessageDTO addItem(String categoryId, AddEntityBody body) {

    AddItemPathAndBody addItemPathAndBody = new AddItemPathAndBody(categoryId, body);

    AddItemOperationRequest addItemRequest = this.addItemRequestTransformer.transform(addItemPathAndBody);

    EntityPersistent entityPersistent = this.addItemOperation.execute(addItemRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public MessageDTO addSynonym(String categoryId, String itemId, AddEntityBody body) {

    AddSynonymPathAndBody addSynonymPathAndBody = new AddSynonymPathAndBody(categoryId, itemId, body);

    AddSynonymOperationRequest addSynonymOperationRequest = this.addSynonymRequestTransformer.transform(addSynonymPathAndBody);

    EntityPersistent entityPersistent = this.addSynonymOperation.execute(addSynonymOperationRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Override
  public MessageDTO addPhrase(String categoryId, String itemId, AddEntityBody body) {

    AddPhraseOperationRequest addPhraseOperationRequest = new AddPhraseOperationRequest();
    addPhraseOperationRequest.setEntity(categoryId);
    addPhraseOperationRequest.setItemId(itemId);
    addPhraseOperationRequest.setDescription(body.getDescription());

    EntityPersistent entityPersistent = this.addPhraseOperation.execute(addPhraseOperationRequest);

    return this.messageDTOTransformer.transform(entityPersistent);
  }

  @Resource
  public void setAddCategoryRequestTransformer(RequestTransformer<AddEntityBody, AddCategoryOperationRequest> addCategoryRequestTransformer) {
    this.addCategoryRequestTransformer = addCategoryRequestTransformer;
  }

  @Resource
  public void setAddItemRequestTransformer(RequestTransformer<AddItemPathAndBody, AddItemOperationRequest> addItemRequestTransformer) {
    this.addItemRequestTransformer = addItemRequestTransformer;
  }

  @Resource
  public void setAddSynonymRequestTransformer(RequestTransformer<AddSynonymPathAndBody, AddSynonymOperationRequest> addSynonymRequestTransformer) {
    this.addSynonymRequestTransformer = addSynonymRequestTransformer;
  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<EntityPersistent, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }

  @Resource
  public void setAddCategoryOperation(Operation<AddCategoryOperationRequest, EntityPersistent> addCategoryOperation) {
    this.addCategoryOperation = addCategoryOperation;
  }

  @Resource
  public void setAddItemOperation(Operation<AddItemOperationRequest, EntityPersistent> addItemOperation) {
    this.addItemOperation = addItemOperation;
  }

  @Resource
  public void setAddSynonymOperation(Operation<AddSynonymOperationRequest, EntityPersistent> addSynonymOperation) {
    this.addSynonymOperation = addSynonymOperation;
  }

  @Resource
  public void setAddPhraseOperation(Operation<AddPhraseOperationRequest, EntityPersistent> addPhraseOperation) {
    this.addPhraseOperation = addPhraseOperation;
  }

}
