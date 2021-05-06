package com.app.dictionary.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "word")
    private String word;

    @Column(name = "morphologyEndings")
    private String morphologyEndings;

    @Column(name = "morphologyCategory")
    private String morphologyCategory;

    @OneToMany(cascade = CascadeType.ALL)
    private List<WordDefinition> definitions;

    @Column(name = "important")
    private boolean important;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMorphologyEndings() {
        return morphologyEndings;
    }

    public void setMorphologyEndings(String morphologyEndings) {
        this.morphologyEndings = morphologyEndings;
    }

    public String getMorphologyCategory() {
        return morphologyCategory;
    }

    public void setMorphologyCategory(String morphologyCategory) {
        this.morphologyCategory = morphologyCategory;
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
                "id=" + id +
                ", word='" + word + '\'' +
                ", morphologyEndings='" + morphologyEndings + '\'' +
                ", morphologyCategory='" + morphologyCategory + '\'' +
                ", definitions=" + definitions +
                ", important=" + important +
                '}';
    }
}
