package unlp.info.chatbot.dto.transformer;

public interface DTOTransformer<I, O> {

  O transform(I in);
}
