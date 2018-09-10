package unlp.info.chatbot.dto.transformer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.StatusResponse;
import unlp.info.chatbot.dto.transformer.request.StatusResponseDTOTransformerRequest;
import unlp.info.chatbot.exception.InternalApiException;

@Component
public class StatusResponseDTOTransformer implements DTOTransformer<StatusResponseDTOTransformerRequest, StatusResponse> {

  @Override
  public StatusResponse transform(StatusResponseDTOTransformerRequest request) {
    String status = request.getStatus();
    String entityId = request.getEntityId();

    if (!"OK".equalsIgnoreCase(status)) {
      throw new InternalApiException("An error has occurred when try to remove entity: " + entityId +  ". Status response: " + status);
    }
    return new StatusResponse(HttpStatus.OK.getReasonPhrase(), "Entity: " + entityId + " -> was remove");
  }
}
