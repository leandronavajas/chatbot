package unlp.info.chatbot.transformer;

import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.ResponseDTO;
import unlp.info.chatbot.operation.request.AddResponseRequest;

@Component
public class ResponseDTOTransformer implements Transformer<AddResponseRequest, ResponseDTO> {

  @Override
  public ResponseDTO transform(AddResponseRequest in) {

    ResponseDTO responseDTO = new ResponseDTO();

    responseDTO.setEntity(in.getEntity());
    responseDTO.setDescription(in.getDescription());
    responseDTO.setQuickReplies(in.getQuickReplies());
    responseDTO.setLinks(in.getLinks());

    return responseDTO;
  }
}
