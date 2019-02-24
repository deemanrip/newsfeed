package com.yukhlin.newsfeed.htmlextractor.model;

import java.time.LocalDate;

public class ExtractedArticleData {

    private String title;
    private String description;
    private String mainImageLink;
    private LocalDate publicationDate;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainImageLink() {
        return mainImageLink;
    }

    public void setMainImageLink(String mainImageLink) {
        this.mainImageLink = mainImageLink;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}