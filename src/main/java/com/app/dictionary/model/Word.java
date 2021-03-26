package com.app.dictionary.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "word")
    private String word;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL)
    private List<WordDefinition> definitions;

    @Column(name = "important")
    private boolean important;

    public long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<WordDefinition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<WordDefinition> definitions) {
        this.definitions = definitions;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", type='" + type + '\'' +
                ", definition='" + definitions + '\'' +
                ", important=" + important +
                '}';
    }
}
