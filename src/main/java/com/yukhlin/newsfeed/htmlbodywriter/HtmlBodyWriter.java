package com.yukhlin.newsfeed.htmlbodywriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class HtmlBodyWriter {

    private static final String EXTRACTION_DIRECTORY = "src\\main\\resources\\static\\extractedhtml\\";

    private String linkTemplate;

    public HtmlBodyWriter(String linkTemplate) {
        this.linkTemplate = linkTemplate;
    }

    public String saveBody(String body, String fileName) {
        try {
            Path file = Paths.get(EXTRACTION_DIRECTORY, fileName);
            Files.write(file, body.getBytes(), StandardOpenOption.CREATE);

            return linkTemplate + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}