package com.app.dictionary.model;

import lombok.Data;

import java.util.List;

@Data
public class Word {
    private String word;
    private String morphologyEndings;
    private String morphologyCategory;
    private String language;
    private List<WordDefinition> definitions;
}
