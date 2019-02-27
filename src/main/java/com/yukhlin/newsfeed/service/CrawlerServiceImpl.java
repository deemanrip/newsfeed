package com.yukhlin.newsfeed.service;

import com.yukhlin.newsfeed.data.entity.Article;
import com.yukhlin.newsfeed.data.repository.ArticleRepository;
import com.yukhlin.newsfeed.htmlbodywriter.HtmlBodyWriter;
import com.yukhlin.newsfeed.htmlextractor.HtmlExtractor;
import com.yukhlin.newsfeed.htmlextractor.model.ExtractedArticleData;
import com.yukhlin.newsfeed.xmlparser.StaxParserWrapper;
import com.yukhlin.newsfeed.xmlparser.model.ParsedArticleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CrawlerServiceImpl implements CrawlerService {

    private StaxParserWrapper staxParserWrapper;
    private HtmlExtractor htmlExtractor;
    private HtmlBodyWriter htmlBodyWriter;
    private ArticleRepository articleRepository;

    @Autowired
    public CrawlerServiceImpl(StaxParserWrapper staxParserWrapper, HtmlExtractor htmlExtractor, HtmlBodyWriter htmlBodyWriter, ArticleRepository articleRepository) {
        this.staxParserWrapper = staxParserWrapper;
        this.htmlExtractor = htmlExtractor;
        this.htmlBodyWriter = htmlBodyWriter;
        this.articleRepository = articleRepository;
    }

    @Override
    public void crawlSites() throws XMLStreamException, IOException {
        List<ParsedArticleItem> links = staxParserWrapper.parseXmlFeed();
        links.stream()
                .filter(articleItem -> !articleRepository.existsBySourceLink(articleItem.getLink()))
                .map(articleItem -> {
                    ExtractedArticleData extraction = htmlExtractor.extractData(articleItem.getLink());

                    String fileName = "/" + UUID.randomUUID() + ".txt";
                    String extractionLink = htmlBodyWriter.saveBody(extraction.getBody(), fileName);

                    return createEntity(extraction, articleItem, extractionLink);
                })
                .forEach(article -> articleRepository.save(article));
    }

    @Override
    public List<Article> getCrawlerResult() {
        return StreamSupport
                .stream(articleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    private Article createEntity(ExtractedArticleData extraction, ParsedArticleItem parsedArticleItem, String extractionLink) {
        Article article = new Article();
        article.setTitle(parsedArticleItem.getTitle());
        article.setSourceLink(parsedArticleItem.getLink());
        article.setMainImageLink(extraction.getMainImageLink());
        article.setExtractionLink(extractionLink);
        article.setPublicationDate(parsedArticleItem.getPubDate());
        article.setDescription(parsedArticleItem.getDescription());

        return article;
    }
}