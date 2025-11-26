package com.neyaank;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            FileWriter finalOut = new FileWriter("..\\ex5-out.json");
            DefaultHandler handler = new JsonHandler(finalOut);
            parser.parse("..\\ex5.xml", handler);
            finalOut.close();
            System.out.println("json created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}