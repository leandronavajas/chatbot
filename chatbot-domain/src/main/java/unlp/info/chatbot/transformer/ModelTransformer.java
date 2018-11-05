package unlp.info.chatbot.transformer;

public interface ModelTransformer<I, O> {

  O transform(I in);

}
