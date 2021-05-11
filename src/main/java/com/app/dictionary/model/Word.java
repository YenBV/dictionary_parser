package com.app.dictionary.model;

import com.app.dictionary.view.WordArticleView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.List;

@Data
@JsonView(WordArticleView.Common.class)
public class Word {
    private String word;

    @JsonIgnore
    private String ngrams;
    private String morphologyEndings;
    private String morphologyCategory;
    private String language;
    private List<WordDefinition> definitions;
}
