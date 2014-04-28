import org.jivesoftware.smack.XMPPException;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws XMPPException {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          new JabberClientForm();
        } catch (XMPPException e) {
          e.printStackTrace();
        } catch (ParserConfigurationException e) {
          e.printStackTrace();
        } catch (SAXException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
