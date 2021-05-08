package com.app.dictionary.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class WordDefinition {

    private String definition;
    private String example;
    private String stylisticMarker;

    /**
     * `*` next to definition number means true.
     */
    private boolean idiosyncraticMeaning;
    private boolean equalMeaning;
    private List<EquivalentWordDefinition> equivalentDefinitions;

    public WordDefinition() {
    }

    public WordDefinition(String definition, @Nullable String example) {
        this.definition = definition;
        this.example = example;
    }
}
