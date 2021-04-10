package com.app.dictionary.model;

import javax.persistence.*;

@Entity
@Table(name = "equivalent_word_definition")
public class EquivalentWordDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "language")
    private String language;

    @Column(name = "definition", nullable = false)
    private String definition;

    public long getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
