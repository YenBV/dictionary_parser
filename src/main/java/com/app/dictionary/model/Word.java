package com.app.dictionary.model;

import lombok.Data;

import java.util.List;

@Data
public abstract class Word {
    private String word;
    private String morphologyEndings;
    private String morphologyCategory;
    private List<WordDefinition> definitions;
    private boolean important;
}
