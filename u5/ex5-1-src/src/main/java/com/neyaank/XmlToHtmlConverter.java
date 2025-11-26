package com.neyaank;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileWriter;

public class XmlToHtmlConverter {
    public static void createHtmlAt(String xmlPathIn, String htmlPathOut) throws Exception {
        //SaxParser due to XMLReaderFactory is deprecated
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        FileWriter out = new FileWriter(htmlPathOut);
        DefaultHandler handler = new XMLHandler(out);
        parser.parse(xmlPathIn, handler);
        out.close();

        System.out.println("html is created");
    }

}
