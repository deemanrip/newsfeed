package com.yukhlin.newsfeed.config;

import com.yukhlin.newsfeed.htmlbodywriter.HtmlBodyWriter;
import com.yukhlin.newsfeed.xmlparser.StaxParserWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
@PropertySource("classpath:crawler.properties")
public class CrawlerConfig implements WebMvcConfigurer {

    @Value("${xml.feed.link}")
    private String xmlFeedLink;
    @Value("${extraction.directory}")
    private String extractionDirectory;
    @Value("${extraction.resource.link}")
    private String extractionResourceLink;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(extractionResourceLink + "/**").
                addResourceLocations(new File(extractionDirectory).toURI().toString());
    }

    @Bean
    public StaxParserWrapper getStaxParserWrapper() {
        return new StaxParserWrapper(xmlFeedLink);
    }

    @Bean
    public HtmlBodyWriter getHtmlBodyWriter() {
        return new HtmlBodyWriter(extractionDirectory, extractionResourceLink);
    }
}