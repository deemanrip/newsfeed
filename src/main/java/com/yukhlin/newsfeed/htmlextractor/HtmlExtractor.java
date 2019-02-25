package com.yukhlin.newsfeed.htmlextractor;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.yukhlin.newsfeed.htmlextractor.model.ExtractedArticleData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class HtmlExtractor {

    public ExtractedArticleData extractData(String sourceLink) {
        ExtractedArticleData extraction = new ExtractedArticleData();

        JBrowserDriver driver = new JBrowserDriver();
        driver.get(sourceLink);
        String pageSource = driver.getPageSource();
        driver.quit();

        Document doc = Jsoup.parse(pageSource);

        extraction.setMainImageLink(extractMainImageLink(doc));
        extraction.setBody(removeScripts(doc.body()));

        return extraction;
    }

    private String extractMainImageLink(Document doc) {
        Elements mainImageEl = doc.select("meta[property=og:image]");
        return mainImageEl.attr("content");
    }

    private String removeScripts(Element body) {
        body.getAllElements().forEach(element -> {
            if (element.tagName().equals("script")) {
                element.remove();
            }
        });

        return body.toString();
    }
}