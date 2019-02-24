package com.yukhlin.newsfeed.config;

import com.yukhlin.newsfeed.htmlbodywriter.HtmlBodyWriter;
import com.yukhlin.newsfeed.xmlparser.StaxParserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@PropertySource("classpath:crawler.properties")
public class CrawlerConfig {

    @Value("${xml.feed.link}")
    private String xmlFeedLink;
    private Environment environment;

    @Autowired
    public CrawlerConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public StaxParserWrapper getStaxParserWrapper() {
        return new StaxParserWrapper(xmlFeedLink);
    }

    @Bean
    public HtmlBodyWriter getHtmlBodyWriter() throws UnknownHostException {
        String localHostAddress = InetAddress.getLocalHost().getHostAddress();
        String serverPort = environment.getProperty("server.port");

        return new HtmlBodyWriter(localHostAddress + ":" + serverPort + "/exctractedhtml");
    }
}