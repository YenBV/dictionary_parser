package com.app.dictionary.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "word")
    private String word;

    @Column(name = "type")
    private String type;

    @OneToMany(cascade = CascadeType.ALL)
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
