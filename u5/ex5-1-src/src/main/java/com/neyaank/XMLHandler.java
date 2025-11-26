package com.neyaank;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;

public class XMLHandler extends DefaultHandler {
    private String currentElement = "";
    private StringBuilder textBuffer = new StringBuilder();
    private FileWriter out;
    public XMLHandler(FileWriter out){
        this.out = out;
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {
        try {
            out.write("<html><head><title>Takeout title</title></head><body><h1>START DOCUMENT!</h1>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void endDocument() throws SAXException {
        try {
            out.write("<h1>END DOCUMENT!</h1></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        print(out);
        currentElement = qName;

        try {
            out.write("<div style='margin-left:8px;'>");
            out.write("&lt;" + qName + "&gt;");

            if (attributes != null && attributes.getLength() > 0) {
                out.write("<ul>");
                for (int i = 0; i < attributes.getLength(); i++) {
                    out.write("<li>" + attributes.getQName(i) + " = \"" +
                            attributes.getValue(i) + "\"</li>");
                }
                out.write("</ul>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        textBuffer.append(ch, start, length);
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        print(out);
        try {
            out.write("&lt;/" + qName + "&gt;<br/></div>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void print(FileWriter out) {
        try {
            if (textBuffer.length() > 0) {
                String text = textBuffer.toString().trim();
                if (!text.isEmpty()) {
                    out.write("<div style='margin-left:10px;'>" + text + "</div>");
                }
                textBuffer.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
