package unlp.info.chatbot.client.parser;

public interface Parser<I,O> {

  O parse(I in);

}
