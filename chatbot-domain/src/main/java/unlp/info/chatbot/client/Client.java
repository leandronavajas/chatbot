package unlp.info.chatbot.client;

public interface Client<I, O> {

  O call(I request);
}
