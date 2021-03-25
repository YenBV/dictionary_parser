package com.app.dictionary.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Word {

    @Id
    private long id;

    @Column(name = "word")
    private String word;

    @Column(name = "type")
    private String type;

    @OneToMany()
    @Column(name = "definitions")
    private List<WordDefinition> definitions;

    @Column(name = "important")
    private boolean important;

    public Word() {
    }

    private Word(Builder builder) {
        this.word = builder.word;
        this.type = builder.type;
        this.definitions = builder.definitions;
        this.important = builder.important;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String word;
        private String type;
        private List<WordDefinition> definitions;
        private boolean important;

        public Builder setWord(String word) {
            this.word = word;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setDefinitions(List<WordDefinition> definitions) {
            this.definitions = definitions;
            return this;
        }

        public Builder setImportant(boolean important) {
            this.important = important;
            return this;
        }

        public Word build() {
            return new Word(this);
        }
    }

    public String getWord() {
        return word;
    }

    public String getType() {
        return type;
    }

    public List<WordDefinition> getDefinitions() {
        return definitions;
    }

    public boolean isImportant() {
        return important;
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
