package com.yukhlin.newsfeed.htmlbodywriter;

import java.io.FileWriter;
import java.io.IOException;

public class HtmlBodyWriter {

    private static final String EXTRACTION_DIRECTORY = "src/resources/static/extractedhtml/";

    private String linkTemplate;

    public HtmlBodyWriter(String linkTemplate) {
        this.linkTemplate = linkTemplate;
    }

    public String saveBody(String body, String fileName) {
        try (FileWriter fileWriter = new FileWriter(EXTRACTION_DIRECTORY + fileName)) {
            fileWriter.write(body);

            return linkTemplate + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}