package com.app.dictionary.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "word_definition")
public class WordDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "definition", nullable = false)
    private String definition;

    @Nullable
    @Column(name = "example")
    private String example;

//    @ManyToOne()
//    private Word word;

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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Nullable
    public String getExample() {
        return example;
    }

    public void setExample(@Nullable String example) {
        this.example = example;
    }
}
