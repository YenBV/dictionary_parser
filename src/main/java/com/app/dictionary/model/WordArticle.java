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

    public WordArticle(
            Word word,
            List<Word> otherLanguageWords) {
        this.otherLanguageWords = otherLanguageWords;
        this.word = word;
    }
}
