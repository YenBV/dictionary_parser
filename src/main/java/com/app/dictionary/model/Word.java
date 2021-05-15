package com.app.dictionary.model;

import com.app.dictionary.view.WordArticleView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@JsonView(WordArticleView.Common.class)
public class Word {
    @NotBlank(message = "Word is required")
    @Size(max = 50)
    private String word;

    @JsonIgnore
    private String ngrams;

    @Size(max = 50)
    private String morphologyEndings;

    @Size(max = 50)
    private String morphologyCategory;

    @Valid
    private List<WordDefinition> definitions;
}
