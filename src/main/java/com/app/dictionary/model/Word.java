package com.app.dictionary.model;

import com.app.dictionary.view.WordArticleView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@JsonView(WordArticleView.Common.class)
public class Word {
    @NotBlank(message = "Word is required")
    private String word;

    @JsonIgnore
    private String ngrams;
    private String morphologyEndings;
    private String morphologyCategory;
    private String language;

    @Valid
    private List<WordDefinition> definitions;
}
