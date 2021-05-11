package com.app.dictionary.model;

import com.app.dictionary.view.WordArticleView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@JsonView(WordArticleView.Common.class)
public class WordDefinition {

    private String definition;
    private String example;
    private String stylisticMarker;

    /**
     * `*` next to definition number means true.
     */
    private boolean idiosyncraticMeaning;
    private boolean equalMeaning;

    @JsonView(WordArticleView.Full.class)
    private List<EquivalentWordDefinition> equivalentDefinitions;

    public WordDefinition() {
    }

    public WordDefinition(String definition, @Nullable String example) {
        this.definition = definition;
        this.example = example;
    }
}
