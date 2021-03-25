package com.app.dictionary.model;

import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "word_definition")
public class WordDefinition {

    @Id
    private long id;

    @Column(name = "definition", nullable = false)
    private String definition;

    @Nullable
    @Column(name = "example")
    private String example;

    public WordDefinition() {
    }

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
