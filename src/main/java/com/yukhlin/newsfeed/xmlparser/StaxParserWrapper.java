package com.yukhlin.newsfeed.xmlparser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StaxParserWrapper {

    private String xmlFeedLink;

    private static final String ITEM_ELEMENT_NAME = "item";
    private static final String LINK_ELEMENT_NAME = "link";

    public StaxParserWrapper(String xmlFeedLink) {
        this.xmlFeedLink = xmlFeedLink;
    }

    public List<String> parseXmlFeed() throws IOException, XMLStreamException {
        List<String> links = new ArrayList<>();

        URL url = new URL(xmlFeedLink);

        try (InputStream inputStream = url.openStream()) {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && isItemElement(reader.getLocalName())) {
                    links.add(parseItem(reader));
                }
            }

            reader.close();
        }

        return links;
    }

    private String parseItem(XMLStreamReader reader) throws XMLStreamException {
        String link = null;

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT && isLinkElement(reader.getLocalName())) {
                link = reader.getElementText();
            } else if (event == XMLStreamConstants.END_ELEMENT && isItemElement(reader.getLocalName())) {
                break;
            }
        }

        return link;
    }

    private boolean isItemElement(String elementName) {
        return elementName.equals(ITEM_ELEMENT_NAME);
    }

    private boolean isLinkElement(String elementName) {
        return elementName.equals(LINK_ELEMENT_NAME);
    }

}