import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tests implements MessageListener {

  XMPPConnection connection;

  public void login(String userName, String password) throws XMPPException {
    ConnectionConfiguration config = new ConnectionConfiguration("jabber.org", 5222, "jabber.org");
    connection = new XMPPConnection(config);

    connection.connect();
    SASLAuthentication.supportSASLMechanism("PLAIN", 0);
    connection.login(userName, password);
  }

  public void sendMessage(String message, String to) throws XMPPException {
    Chat chat = connection.getChatManager().createChat(to, this);
    chat.sendMessage(message);
  }

  public void displayBuddyList() {
    Roster roster = connection.getRoster();
    Collection<RosterEntry> entries = roster.getEntries();

    System.out.println("\n\n" + entries.size() + " buddy(ies):");
    for (RosterEntry r : entries) {
      System.out.println(r.getUser());
    }
  }

  public void disconnect() {
    connection.disconnect();
  }

  public void processMessage(Chat chat, Message message) {
    if (message.getType() == Message.Type.chat) {
      System.out.println(chat.getParticipant() + " says: " + message.getBody());
      try {
        chat.sendMessage(message.getBody() + " echo");
      } catch (XMPPException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static void main(String args[]) throws XMPPException, IOException {
    // declare variables
    Tests c = new Tests();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String msg;

    // turn on the enhanced debugger
    XMPPConnection.DEBUG_ENABLED = true;

    // Enter your login information here
    c.login("PavlikSvolochkov", "Gfdkbr2211");

    c.displayBuddyList();

    System.out.println("-----");

    System.out.println("Who do you want to talk to? - Type contacts full email address:");
    String talkTo = br.readLine();

    System.out.println("-----");
    System.out.println("All messages will be sent to " + talkTo);
    System.out.println("Enter your message in the console:");
    System.out.println("-----\n");

    while (!(msg = br.readLine()).equals("bye")) {
      //c.sendMessage(msg, talkTo);
      c.sendMessage("Hello, Ulysses!", "ulysses.test@jabber.ru");
    }

    c.disconnect();
    System.exit(0);
  }
}