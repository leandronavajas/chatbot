package unlp.info.chatbot.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import unlp.info.chatbot.dto.MessageDTO;
import unlp.info.chatbot.exception.LowConfidenceException;
import unlp.info.chatbot.exception.NullConfidenceException;
import unlp.info.chatbot.operation.request.GetMessageRequest;
import unlp.info.chatbot.service.RepositoryService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class GetMessageOperation implements Operation<GetMessageRequest, MessageDTO> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetMessageOperation.class);
  private static BigDecimal CONFIDENCE_THRESHOLD = BigDecimal.valueOf(0.8);

  private RepositoryService<MessageDTO> repositoryService;

  @Override
  public MessageDTO execute(GetMessageRequest request) {

    BigDecimal confidence = request.getConfidence();

    if (confidence == null) {
      LOGGER.error("[GET MESSAGE OPERATION] Confidence cannot be null");
      throw new NullConfidenceException("Confidence cannot be null");
    }

    if (CONFIDENCE_THRESHOLD.compareTo(confidence) > 0) {
      LOGGER.error("[GET MESSAGE OPERATION] Confidence must be upper than 80%. Confidence: {}", confidence);
      throw new LowConfidenceException("Confidence must be upper than 80%");
    }

    return this.repositoryService.getById(request.getEntity());
  }

  @Resource
  public void setRepositoryService(RepositoryService<MessageDTO> repositoryService) {
    this.repositoryService = repositoryService;
  }
}