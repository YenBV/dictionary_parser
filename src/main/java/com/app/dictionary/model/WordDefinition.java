package com.app.dictionary.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class WordDefinition {
    private long id;
    private String definition;
    private String example;
    private String stylisticMeaning;

    /**
     * `*` next to definition number means true.
     */
    private boolean idiosyncraticMeaning;
    private List<EquivalentWordDefinition> equivalentDefinitions;

    public WordDefinition() {
    }

    public WordDefinition(String definition) {
        this(definition, null);
    }

    public WordDefinition(String definition, @Nullable String example) {
        this.definition = definition;
        this.example = example;
    }
}
