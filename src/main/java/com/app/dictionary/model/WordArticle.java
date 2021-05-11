package com.app.dictionary.model;

import com.app.dictionary.view.WordArticleView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.List;

@Data
@JsonView(WordArticleView.Common.class)
public class WordArticle {
    private String id;
    private Word word;
    private List<Word> otherLanguageWords;
    private boolean falseParallel;

    public WordArticle() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public WordArticle(Builder builder) {
        this.word = builder.word;
        this.otherLanguageWords = builder.otherLanguageWords;
        this.falseParallel = builder.falseParallel;
    }

    public static final class Builder {
        private Word word;
        private List<Word> otherLanguageWords;
        private boolean falseParallel;

        private Builder() {
        }

        public Builder setWord(Word word) {
            this.word = word;
            return this;
        }

        public Builder setOtherLanguageWords(List<Word> otherLanguageWords) {
            this.otherLanguageWords = otherLanguageWords;
            return this;
        }

        public Builder setFalseParallel(boolean falseParallel) {
            this.falseParallel = falseParallel;
            return this;
        }

        public WordArticle build() {
            return new WordArticle(this);
        }
    }
}
