package unlp.info.chatbot.controller.body.transformer;

public interface RequestTransformer<I,O> {

  O transform(I in);

}
