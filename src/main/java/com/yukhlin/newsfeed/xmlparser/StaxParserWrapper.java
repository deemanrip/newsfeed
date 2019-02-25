package com.yukhlin.newsfeed.xmlparser;

import com.yukhlin.newsfeed.xmlparser.model.ParsedArticleItem;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StaxParserWrapper {

    private String xmlFeedLink;

    private static final String ITEM_ELEMENT_NAME = "item";
    private static final String TITLE_ELEMENT_NAME = "title";
    private static final String LINK_ELEMENT_NAME = "link";
    private static final String DESCRIPTION_ELEMENT_NAME = "description";
    private static final String PUB_DATE_ELEMENT_NAME = "pubDate";

    public StaxParserWrapper(String xmlFeedLink) {
        this.xmlFeedLink = xmlFeedLink;
    }

    public List<ParsedArticleItem> parseXmlFeed() throws IOException, XMLStreamException {
        List<ParsedArticleItem> parsedArticleItems = new ArrayList<>();

        URL url = new URL(xmlFeedLink);

        try (InputStream inputStream = url.openStream()) {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && isItemElement(reader.getLocalName())) {
                    parsedArticleItems.add(parseItem(reader));
                }
            }

            reader.close();
        }

        return parsedArticleItems;
    }

    private ParsedArticleItem parseItem(XMLStreamReader reader) throws XMLStreamException {
        ParsedArticleItem parsedArticleItem = new ParsedArticleItem();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT && isTitleElement(reader.getLocalName())) {
                parsedArticleItem.setTitle(reader.getElementText());
            } else if (event == XMLStreamConstants.START_ELEMENT && isLinkElement(reader.getLocalName())) {
                parsedArticleItem.setLink(reader.getElementText());
            } else if (event == XMLStreamConstants.START_ELEMENT && isDescriptionElement(reader.getLocalName())) {
                parsedArticleItem.setDescription(reader.getElementText());
            } else if (event == XMLStreamConstants.START_ELEMENT && isPubDateElement(reader.getLocalName())) {
                parsedArticleItem.setPubDate(LocalDate.parse(reader.getElementText(), DateTimeFormatter.RFC_1123_DATE_TIME));
            } else if (event == XMLStreamConstants.END_ELEMENT && isItemElement(reader.getLocalName())) {
                break;
            }

        }

        return parsedArticleItem;
    }

    private boolean isItemElement(String elementName) {
        return elementName.equals(ITEM_ELEMENT_NAME);
    }

    private boolean isTitleElement(String elementName) {
        return elementName.equals(TITLE_ELEMENT_NAME);
    }

    private boolean isLinkElement(String elementName) {
        return elementName.equals(LINK_ELEMENT_NAME);
    }

    private boolean isDescriptionElement(String elementName) {
        return elementName.equals(DESCRIPTION_ELEMENT_NAME);
    }

    private boolean isPubDateElement(String elementName) {
        return elementName.equals(PUB_DATE_ELEMENT_NAME);
    }

}