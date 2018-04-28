package unlp.info.chatbot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unlp.info.chatbot.annotation.RequestTracking;

@Aspect
public class RequestTrackingAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(RequestTrackingAspect.class);

  @Pointcut("@annotation(requestTracking)")
  public void webLayerOperations(RequestTracking requestTracking) {}

  @Before("webLayerOperations(requestTracking)")
  public void beforeMethodController(JoinPoint joinPoint, RequestTracking requestTracking) {

    String tracking =
        String.format(
            "uow:%s, requestId:%s, method:%s, request:%s",
            RequestUtils.getUow(),
            this.getRequestId(),
            RequestUtils.getCurrentMethod(),
            RequestUtils.getCurrentUrl());
    LOGGER.info(tracking);

  }

  private String getRequestId() {
    // TODO: LN falta request id, sino eliminar
    return "TEST-request_id";
  }

}
