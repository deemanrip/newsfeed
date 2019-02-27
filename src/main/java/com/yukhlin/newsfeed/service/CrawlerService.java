package com.yukhlin.newsfeed.service;

import com.yukhlin.newsfeed.data.entity.Article;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface CrawlerService {

    void crawlSites() throws XMLStreamException, IOException;
    List<Article> getCrawlerResult();

}
