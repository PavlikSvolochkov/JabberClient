import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromContainsFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class JabberClient {

  private static final int packetReplyTimeout = 500; // millis

  private static String server = "jabber.ru";
  private static String message = null;

  private int port = 5222;

  File file = new File("src/response.xml");

  private ConnectionConfiguration config;
  private XMPPConnection connection;

  private ChatManager chatManager;
  private MessageListener messageListener;

  FileWriter writer;

  private JabberClientForm form;

  public JabberClient(String server, int port) {
    this.server = server;
    this.port = port;
  }

  public void connect(String login, String pass) throws XMPPException {
    System.out.println("Connecting to server...");
    ConnectionConfiguration config = new ConnectionConfiguration(server, port, "jabber.ru");
    // Внимание! Следующая строчка очень важна!
    SASLAuthentication.supportSASLMechanism("PLAIN");
    connection = new XMPPConnection(config);
    connection.connect();
    connection.login(login, pass); // т.е. не login@jabber.ru, а просто login
    System.out.println("Connection established!");
  }

  public void performLogin(String username, String password) throws XMPPException {
    if (connection != null && connection.isConnected()) {
      connection.login(username, password);
    }
  }

  public void setStatus(boolean available, String status) {
    Presence.Type type = available ? Presence.Type.available : Presence.Type.unavailable;
    Presence presence = new Presence(type);
    presence.setStatus(status);
    connection.sendPacket(presence);
  }

  public String receiveMessage() {
    PacketFilter filter = new AndFilter(new PacketTypeFilter(Message.class), new FromContainsFilter("ulysses.test@jabber.ru"));
    // Collect these messages
    PacketCollector collector = connection.createPacketCollector(filter);
    Packet packet = collector.nextResult();
    Message msg = null;
    if (packet instanceof Message) {
      msg = (Message) packet; // Process message...
      System.out.println(msg);
    }
    return msg.toString();
  }

  public void sendMessage(String message, String buddyJID) throws XMPPException {
    System.out.println(String.format("Sending mesage '%1$s' to user %2$s", message, buddyJID));

    Chat chat = connection.getChatManager().createChat(buddyJID, new MessageListener() {
      public void processMessage(Chat chat, Message message) {
        //System.out.println("Received message: " + message);
        setMessage(message.getBody());
        System.out.println(message.getFrom() + " >>> " + message.getBodies() + "\n");
      }
    });
    chat.sendMessage(message);
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String msg) {
    this.message = msg;
  }

  public void createEntry(String user, String name) throws Exception {
    System.out.println(String.format("Creating entry for buddy '%1$s' with name %2$s", user, name));
    Roster roster = connection.getRoster();
    roster.createEntry(user, name, null);
  }

  class MyMessageListener implements MessageListener {

    @Override
    public void processMessage(Chat chat, Message message) {
      String from = message.getFrom();
      String body = message.getBody();
      System.out.println(String.format("Received message '%1$s' from %2$s", body, from));
    }
  }

  public void destroy() {
    if (connection != null && connection.isConnected()) {
      connection.disconnect();
    }
  }
}