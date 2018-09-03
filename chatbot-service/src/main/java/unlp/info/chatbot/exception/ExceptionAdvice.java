package unlp.info.chatbot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import unlp.info.chatbot.dto.StatusResponse;

@ControllerAdvice
public class ExceptionAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

  @ExceptionHandler({ApiException.class})
  public ResponseEntity<StatusResponse> handleNotFoundException(ApiException exception) {

    LOGGER.error("[ADVICE] Exception handler. Make Api response. Error Code: {} - Error message: {}", exception.getCode(), exception.getCauses());
    StatusResponse responseStatus = new StatusResponse(exception.getStatus(), exception.getCauses());
    return ResponseEntity.status(exception.getCode()).body(responseStatus);
  }

}
