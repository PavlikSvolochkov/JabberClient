package handlers;

import helpers.UserSerponse;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ResponseHandler extends DefaultHandler {

  private String tempValue = null;

  private UserSerponse response = null;

  @Override
  public void startDocument() throws SAXException {
    System.out.println("START DOCUMENT PARSING");
  }

  @Override
  public void endDocument() throws SAXException {
    System.out.println("END DOCUMENT PARSING");
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equals("message")) {
      response = new UserSerponse();
      response.setMessageLang(attributes.getValue("lang"));
      response.setMessageId(attributes.getValue("id"));
      response.setMessageTo(attributes.getValue("to"));
      response.setMessageFrom(attributes.getValue("from"));
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equals("body"))
      response.setBody(tempValue);
    if (qName.equals("thread"))
      response.setThread(tempValue);
    if (qName.equals("active"))
      response.setActive(tempValue);
    if (qName.equals("nick"))
      response.setNick(tempValue);
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    tempValue = new String(ch, start, length);
  }

  public UserSerponse getResponse() {
    return response;
  }
}
