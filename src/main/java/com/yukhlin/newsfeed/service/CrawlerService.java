package com.yukhlin.newsfeed.service;

import com.yukhlin.newsfeed.data.entity.Article;
import com.yukhlin.newsfeed.data.repository.ArticleRepository;
import com.yukhlin.newsfeed.htmlbodywriter.HtmlBodyWriter;
import com.yukhlin.newsfeed.htmlextractor.HtmlExtractor;
import com.yukhlin.newsfeed.htmlextractor.model.ExtractedArticleData;
import com.yukhlin.newsfeed.xmlparser.StaxParserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CrawlerService {

    private StaxParserWrapper staxParserWrapper;
    private HtmlExtractor htmlExtractor;
    private HtmlBodyWriter htmlBodyWriter;
    private ArticleRepository articleRepository;

    @Autowired
    public CrawlerService(StaxParserWrapper staxParserWrapper, HtmlExtractor htmlExtractor, HtmlBodyWriter htmlBodyWriter, ArticleRepository articleRepository) {
        this.staxParserWrapper = staxParserWrapper;
        this.htmlExtractor = htmlExtractor;
        this.htmlBodyWriter = htmlBodyWriter;
        this.articleRepository = articleRepository;
    }

    public void crawlSites() throws XMLStreamException, IOException {
        List<String> links = staxParserWrapper.parseXmlFeed();
        links.stream()
                .map(link -> htmlExtractor.extractData(link))
                .map(extraction -> {
                    String fileName = extraction.getTitle().replace(" ", "-") + ".html";
                    String extractionLink = htmlBodyWriter.saveBody(extraction.getBody(), fileName);

                    return createEntity(extraction, extractionLink);
                })
                .forEach(article -> articleRepository.save(article));
    }

    public List<Article> getCrawlingResult() {
        return StreamSupport
                .stream(articleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    private Article createEntity(ExtractedArticleData extraction, String extractionLink) {
        Article article = new Article();
        article.setTitle(extraction.getTitle());
        article.setSourceLink(extraction.getSourceLink());
        article.setMainImageLink(extraction.getMainImageLink());
        article.setExtractionLink(extractionLink);
        article.setPublicationDate(extraction.getPublicationDate());
        article.setDescription(extraction.getDescription());

        return article;
    }
}