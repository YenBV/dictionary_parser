package com.app.dictionary.dto;

import com.app.dictionary.model.WordDefinition;
import lombok.Data;

import java.util.List;

@Data
public class WordDTO {

    private String word;
    private String morphologyEndings;
    private String morphologyCategory;
    private List<WordDefinition> definitions;
    private boolean important;
    private String language;
}
