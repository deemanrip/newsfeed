package com.yukhlin.newsfeed.data.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "extraction_link", length = 2000, nullable = false)
    private String extractionLink;

    @Column(name = "main_image_link", length = 2000, nullable = false)
    private String mainImageLink;

    @Column(name = "source_link", length = 2000, nullable = false)
    private String sourceLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getExtractionLink() {
        return extractionLink;
    }

    public void setExtractionLink(String extractionLink) {
        this.extractionLink = extractionLink;
    }

    public String getMainImageLink() {
        return mainImageLink;
    }

    public void setMainImageLink(String mainImageLink) {
        this.mainImageLink = mainImageLink;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }
}