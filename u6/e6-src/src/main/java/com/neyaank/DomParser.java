package com.neyaank;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DomParser {
    private DocumentBuilderFactory factory;
    private Document doc;
    private DocumentBuilder builder;
    private int totalGold = 0;
    private int totalSilver = 0;
    private int totalBronze = 0;
    private int totalParticipants = 0;
    private int totalTeams = 0;

    public DomParser() {
        factory = DocumentBuilderFactory.newInstance();
    }

    public void open(File in) {
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(in);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
        }
    }

    public void collectStats() {
        NodeList sportsList = doc.getElementsByTagName("sport");

        for (int i = 0; i < sportsList.getLength(); i++) {
            Element sport = (Element) sportsList.item(i);
            String type = sport.getAttribute("type");
            if ("team".equals(type)) {

                NodeList teams = sport.getElementsByTagName("team");
                totalTeams += teams.getLength();
                NodeList players = sport.getElementsByTagName("player");
                totalParticipants += players.getLength();
            } else if ("solo".equals(type)) {

                NodeList athletes = sport.getElementsByTagName("athlete");
                totalParticipants += athletes.getLength();
                for (int j = 0; j < athletes.getLength(); j++) {

                    Element athlete = (Element) athletes.item(j);
                    totalGold += getInt(athlete, "gold");
                    totalSilver += getInt(athlete, "silver");
                    totalBronze += getInt(athlete, "bronze");
                }
            }
        }
    }

    public void createOutput(File out) {
        try {
            Element statsRoot = doc.createElement("olympics-stats");
            Element participation = doc.createElement("participants");
            participation.setAttribute("total-people", String.valueOf(totalParticipants));
            participation.setAttribute("total-teams", String.valueOf(totalTeams));
            statsRoot.appendChild(participation);
            Element medals = doc.createElement("medals");
            medals.setAttribute("gold", String.valueOf(totalGold));
            medals.setAttribute("silver", String.valueOf(totalSilver));
            medals.setAttribute("bronze", String.valueOf(totalBronze));
            medals.setAttribute("total-medals", String.valueOf(totalGold + totalSilver + totalBronze));
            statsRoot.appendChild(medals);
            doc.getDocumentElement().appendChild(statsRoot);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);

            System.out.println("ex6-out.xml created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getInt(Element parent, String tagName) {
        NodeList list = parent.getElementsByTagName(tagName);
        if (list.getLength() > 0) {
            String content = list.item(0).getTextContent();
            try {
                return Integer.parseInt(content);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}
