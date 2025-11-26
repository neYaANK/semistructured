package com.neyaank;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;

public class JsonHandler extends DefaultHandler {

    private int sportCount = 0;
    private int teamCount = 0;
    private int athleteCount = 0;

    private int goldTotal = 0;
    private int silverTotal = 0;
    private int bronzeTotal = 0;

    private String currentElement = "";
    private int tempGold = 0;
    private String tempFirstname = "", tempLastname = "";

    private int bestGold = -1;
    private String bestAthlete = "";

    private FileWriter finalOutput;

    public JsonHandler(FileWriter finalOutput) {
        this.finalOutput = finalOutput;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            writeOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        currentElement = qName;

        if (qName.equals("sport")) sportCount++;
        if (qName.equals("team")) teamCount++;
        if (qName.equals("athlete")) athleteCount++;
        if (qName.equals("gold")) tempGold = 0;
        if (qName.equals("firstname")) tempFirstname = "";
        if (qName.equals("lastname")) tempLastname = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length).trim();
        if (text.isEmpty()) return;

        switch (currentElement) {
            case "firstname":
                tempFirstname = text;
                break;
            case "lastname":
                tempLastname = text;
            break;
            case "gold":
                tempGold = Integer.parseInt(text);
            break;
            case "silver":
                silverTotal += Integer.parseInt(text);
            break;
            case "bronze":
                bronzeTotal += Integer.parseInt(text);
            break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equals("medals")) {
            goldTotal += tempGold;
            if (tempGold > bestGold) {
                bestGold = tempGold;
                bestAthlete = tempFirstname + " " + tempLastname;
            }
        }
    }

    private void writeOutput() throws Exception {
        FileWriter out = finalOutput;

        out.write("{\n");
        out.write("\t\"sports\": " + sportCount + ",\n");
        out.write("\t\"teams\": " + teamCount + ",\n");
        out.write("\t\"athletes\": " + athleteCount + ",\n");
        out.write("\t\"medals\": {\n");
        out.write("\t\t\"gold\": " + goldTotal + ",\n");
        out.write("\t\t\"silver\": " + silverTotal + ",\n");
        out.write("\t\t\"bronze\": " + bronzeTotal + "\n");
        out.write("\t},\n");
        out.write("\t\"topAthlete\": {\n");
        out.write("\t\t\"name\": \"" + bestAthlete + "\",\n");
        out.write("\t\t\"gold\": " + bestGold + "\n");
        out.write("\t}\n");
        out.write("}\n");
    }
}