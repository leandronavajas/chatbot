package unlp.info.chatbot.dto.transformer;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import unlp.info.chatbot.dto.QuickReplyDTO;

import java.util.List;

@Component
public class QuickReplyDTOTransformer implements DTOTransformer<List<String>, List<QuickReplyDTO>> {

  @Override
  public List<QuickReplyDTO> transform(List<String> in) {
    List<QuickReplyDTO> out = Lists.newArrayList();

    if (CollectionUtils.isEmpty(in)) {
      return out;
    }

    for (String child : in) {
      QuickReplyDTO quickReplyDTO = new QuickReplyDTO();

      quickReplyDTO.setContentType("text");
      quickReplyDTO.setTitle(child);
      quickReplyDTO.setPayload(child);

      out.add(quickReplyDTO);
    }
    return out;
  }

}
