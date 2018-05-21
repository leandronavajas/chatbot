package unlp.info.chatbot.transformer;

public interface PersistentTransformer<I, O> {

  O transform(I in);

}
