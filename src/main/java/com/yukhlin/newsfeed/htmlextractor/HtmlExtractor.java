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
        extraction.setSourceLink(sourceLink);

        JBrowserDriver driver = new JBrowserDriver();
        driver.get(sourceLink);
        String pageSource = driver.getPageSource();
        driver.quit();

        Document doc = Jsoup.parse(pageSource);

        Elements titleEl = doc.select("meta[property=og:title]");
        String title = titleEl.attr("content");
        extraction.setTitle(title);

        Elements mainImageEl = doc.select("meta[property=og:image]");
        String mainImageUrl = mainImageEl.attr("content");
        extraction.setMainImageLink(mainImageUrl);

        Elements descriptionEl = doc.select("meta[name=description]");
        String description = descriptionEl.attr("content");
        extraction.setDescription(description);

        Element body = doc.body();
        body.getAllElements().forEach(element -> {
            if (element.tagName().equals("script")) {
                element.remove();
            }
        });
        extraction.setBody(body.toString());

        return extraction;
    }
}