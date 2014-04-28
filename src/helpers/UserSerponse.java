package helpers;

public class UserSerponse {

  private String body = null;
  private String nick = null;
  private String thread = null;
  private String active = null;
  private String messageLang = null;
  private String messageId = null;
  private String messageTo = null;
  private String messageFrom = null;

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public String getThread() {
    return thread;
  }

  public void setThread(String thread) {
    this.thread = thread;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String getMessageLang() {
    return messageLang;
  }

  public void setMessageLang(String messageLang) {
    this.messageLang = messageLang;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getMessageTo() {
    return messageTo;
  }

  public void setMessageTo(String messageTo) {
    this.messageTo = messageTo;
  }

  public String getMessageFrom() {
    return messageFrom;
  }

  public void setMessageFrom(String messageFrom) {
    this.messageFrom = messageFrom;
  }

  @Override
  public String toString() {
    return "UserSerponse{" +
            "body='" + body + '\'' +
            ", nick='" + nick + '\'' +
            ", thread='" + thread + '\'' +
            ", active='" + active + '\'' +
            ", messageLang='" + messageLang + '\'' +
            ", messageId='" + messageId + '\'' +
            ", messageTo='" + messageTo + '\'' +
            ", messageFrom='" + messageFrom + '\'' +
            '}';
  }
}
