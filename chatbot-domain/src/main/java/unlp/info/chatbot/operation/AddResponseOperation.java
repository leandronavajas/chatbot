package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.ResponseDTO;
import unlp.info.chatbot.exception.NullEntityException;
import unlp.info.chatbot.operation.request.AddResponseRequest;
import unlp.info.chatbot.service.RepositoryService;
import unlp.info.chatbot.transformer.Transformer;

import javax.annotation.Resource;

@Component
public class AddResponseOperation implements Operation<AddResponseRequest, ResponseDTO>{

  private static final Logger LOGGER = LoggerFactory.getLogger(AddResponseOperation.class);

  private RepositoryService<ResponseDTO> repositoryService;

  private Transformer<AddResponseRequest, ResponseDTO> responseDTOTransformer;

  @Override
  public ResponseDTO execute(AddResponseRequest request) {

    if (request.getEntity() == null) {
      LOGGER.error("[ADD RESPONSE OPERATION] Entity cannot be null");
      throw new NullEntityException("Entity cannot be null");
    }

    ResponseDTO responseDTO = this.responseDTOTransformer.transform(request);

    this.repositoryService.save(responseDTO);

    return responseDTO;
  }

  @Resource
  public void setRepositoryService(RepositoryService<ResponseDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }

  @Resource
  public void setResponseDTOTransformer(Transformer<AddResponseRequest, ResponseDTO> responseDTOTransformer) {
    this.responseDTOTransformer = responseDTOTransformer;
  }
}
