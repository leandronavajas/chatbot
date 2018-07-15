package unlp.info.chatbot.facade.impl;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.client.request.AddValueEntityWitRequest;
import unlp.info.chatbot.client.response.AddEntityWitResponse;
import unlp.info.chatbot.controller.body.AddEntityBody;
import unlp.info.chatbot.controller.body.transformer.RequestTransformer;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.dto.transformer.DTOTransformer;
import unlp.info.chatbot.facade.EntityFacade;
import unlp.info.chatbot.model.MessagePersistent;
import unlp.info.chatbot.operation.Operation;
import unlp.info.chatbot.operation.request.AddMessageRequest;
import unlp.info.chatbot.operation.request.AddValueEntityOperationRequest;

import javax.annotation.Resource;

@Component
public class EntityFacadeImpl implements EntityFacade {

  private RequestTransformer<AddEntityBody, AddMessageRequest> addMessageRequestTransformer;
  private Operation<AddMessageRequest, MessagePersistent> addEntityOperation;
  private DTOTransformer<MessagePersistent, MessageDTO> messageDTOTransformer;

  private Operation<AddValueEntityOperationRequest, AddEntityWitResponse> addValueEntityOperation;

  @Override
  public MessageDTO addEntity(AddEntityBody body) {

    AddMessageRequest addMessageRequest = this.addMessageRequestTransformer.transform(body);

    MessagePersistent messagePersistent = this.addEntityOperation.execute(addMessageRequest);

    return this.messageDTOTransformer.transform(messagePersistent);
  }

  @Override
  public AddEntityWitResponse addValueForEntity(String entity, AddValueEntityWitRequest body) {

    AddValueEntityOperationRequest request = new AddValueEntityOperationRequest();
    request.setEntity(entity);
    request.setBody(body);

    return this.addValueEntityOperation.execute(request);
  }


  @Resource
  public void setAddMessageRequestTransformer(RequestTransformer<AddEntityBody, AddMessageRequest> addMessageRequestTransformer) {
    this.addMessageRequestTransformer = addMessageRequestTransformer;
  }

  @Resource
  public void setAddEntityOperation(Operation<AddMessageRequest, MessagePersistent> addEntityOperation) {
    this.addEntityOperation = addEntityOperation;
  }

  @Resource
  public void setMessageDTOTransformer(DTOTransformer<MessagePersistent, MessageDTO> messageDTOTransformer) {
    this.messageDTOTransformer = messageDTOTransformer;
  }

  @Resource
  public void setAddValueEntityOperation(Operation<AddValueEntityOperationRequest, AddEntityWitResponse> addValueEntityOperation) {
    this.addValueEntityOperation = addValueEntityOperation;
  }
}
