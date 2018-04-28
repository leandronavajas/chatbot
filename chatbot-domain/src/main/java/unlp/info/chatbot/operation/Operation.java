package unlp.info.chatbot.operation;

public interface Operation<R,S> {

  S execute(R request);

}
