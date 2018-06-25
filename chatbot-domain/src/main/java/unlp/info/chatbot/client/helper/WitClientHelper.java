package unlp.info.chatbot.client.helper;

public class WitClientHelper {

  public static String replaceSpaces(String originalMessage) {
    return originalMessage.replaceAll(" ", "%20");
  }

}
