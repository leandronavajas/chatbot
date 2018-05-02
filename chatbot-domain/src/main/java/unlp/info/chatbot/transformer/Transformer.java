package unlp.info.chatbot.transformer;

public interface Transformer<I, O> {

  O transform(I in);

}
