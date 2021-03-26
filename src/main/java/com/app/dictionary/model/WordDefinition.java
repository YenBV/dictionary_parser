package com.app.dictionary.model;

import org.springframework.lang.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Word word;

    public WordDefinition() {
    }

    public WordDefinition(String definition) {
        this(definition, null);
    }

    public WordDefinition(String definition, @Nullable String example) {
        this.definition = definition;
        this.example = example;
    }

    public long getId() {
        return id;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setExample(@Nullable String example) {
        this.example = example;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
