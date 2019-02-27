package com.yukhlin.newsfeed.controller;

import com.yukhlin.newsfeed.data.entity.Article;
import com.yukhlin.newsfeed.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/crawler", consumes = "application/json", produces = "application/json")
public class CrawlerController {

    private CrawlerService crawlerService;

    @Autowired
    public CrawlerController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @PostMapping
    public ResponseEntity<String> crawlSites() throws XMLStreamException, IOException {
        crawlerService.crawlSites();
        return new ResponseEntity<>("Sites crawled successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getCrawlerResult() {
        return new ResponseEntity<>(crawlerService.getCrawlerResult(), HttpStatus.OK);
    }
}