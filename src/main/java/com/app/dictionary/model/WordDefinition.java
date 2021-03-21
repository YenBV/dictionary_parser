package com.app.dictionary.model;

import org.springframework.lang.Nullable;

public class WordDefinition {

    private final String definition;

    @Nullable
    private final String example;

    public WordDefinition(String definition) {
        this(definition, null);
    }

    public WordDefinition(String definition, @Nullable String example) {
        this.definition = definition;
        this.example = example;
    }

    public String getDefinition() {
        return definition;
    }

    @Nullable
    public String getExample() {
        return example;
    }
}
