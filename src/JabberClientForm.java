import org.jivesoftware.smack.XMPPException;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class JabberClientForm {

  private JFrame frame;
  private JLabel label;
  private JTextArea textArea1;
  private JTextField textField1;
  private JButton sendMessageButton;
  private JScrollPane scrollPane;
  private JPanel middlePanel;
  private JabberClient client;

  public JabberClientForm() throws XMPPException, ParserConfigurationException, SAXException, IOException {

    middlePanel = new JPanel();
    middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "User name"));

    frame = new JFrame("Simple Jabber Client");
    frame.setSize(665, 405);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new FlowLayout());

    scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.add(textArea1);

    label = new JLabel("Name");

    textArea1 = new JTextArea(20, 20);
    textArea1.setEditable(false);

    textField1 = new JTextField(20);
    textField1.setActionCommand("enter_field");

    sendMessageButton = new JButton("Send message");
    sendMessageButton.setActionCommand("enter_button");
    sendMessageButton.setRequestFocusEnabled(false);

    middlePanel.add(label);
    middlePanel.add(textArea1);
    middlePanel.add(textField1);
    middlePanel.add(sendMessageButton);

    frame.add(middlePanel);

    frame.setVisible(true);

    client = new JabberClient("jabber.ru", 5222);
    client.connect("PavlikSvolochkov", "Gfdkbr2211");
    client.setStatus(true, "Hello, i'm here!");
    client.sendMessage("jabber-jabber", "ulysses.test@jabber.ru");

    sendMessageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          client.sendMessage(textField1.getText(), "ulysses.test@jabber.ru");
          textArea1.append(textField1.getText());
          textArea1.append(client.getMessage() + "\n");
        } catch (XMPPException e1) {
          e1.printStackTrace();
        }
      }
    });
  }
}
