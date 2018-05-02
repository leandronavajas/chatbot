package unlp.info.chatbot.controller.body.transformer;

public interface Transformer<I,O> {

  O transform(I in);

}
