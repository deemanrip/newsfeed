package com.yukhlin.newsfeed.htmlbodywriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class HtmlBodyWriter {

    private String linkTemplate;
    private String extractionDirectory;

    public HtmlBodyWriter(String linkTemplate, String extractionDirectory) {
        this.linkTemplate = linkTemplate;
        this.extractionDirectory = extractionDirectory;
    }

    public String getLinkTemplate() {
        return linkTemplate;
    }

    public String getExtractionDirectory() {
        return extractionDirectory;
    }

    public String saveBody(String body, String fileName) {
        try {
            Path file = Paths.get(extractionDirectory, fileName);
            Files.write(file, body.getBytes(), StandardOpenOption.CREATE);

            return linkTemplate + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}