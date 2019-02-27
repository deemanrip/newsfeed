package com.yukhlin.newsfeed.htmlbodywriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class HtmlBodyWriter {

    private String extractionDirectory;
    private String extractionResourceLink;

    public HtmlBodyWriter(String extractionDirectory, String extractionResourceLink) {
        this.extractionDirectory = extractionDirectory;
        this.extractionResourceLink = extractionResourceLink;
    }

    public String getExtractionDirectory() {
        return extractionDirectory;
    }

    public String saveBody(String body, String fileName) {
        try {
            Path file = Paths.get(extractionDirectory, fileName);
            Files.write(file, body.getBytes(), StandardOpenOption.CREATE);

            return extractionResourceLink + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}