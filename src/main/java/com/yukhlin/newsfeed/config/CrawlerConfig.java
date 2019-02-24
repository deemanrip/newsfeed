package com.yukhlin.newsfeed.config;

import com.yukhlin.newsfeed.xmlparser.StaxParserWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:crawler.properties")
public class CrawlerConfig {

    @Value("${xml.feed.link}")
    private String xmlFeedLink;

    @Bean
    public StaxParserWrapper getStaxParserWrapper() {
        return new StaxParserWrapper(xmlFeedLink);
    }
}