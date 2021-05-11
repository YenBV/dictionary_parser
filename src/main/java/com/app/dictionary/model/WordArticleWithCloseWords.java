package com.app.dictionary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WordArticleWithCloseWords {
    private WordWithId leftWord;
    private WordArticle wordArticle;
    private WordWithId rightWord;

    public WordArticleWithCloseWords(WordArticle leftWordAsArticle, WordArticle wordArticle, WordArticle rightWordAsArticle) {
        if (leftWordAsArticle != null) {
            WordWithId leftWord = new WordWithId();
            leftWord.setId(leftWordAsArticle.getId());
            leftWord.setWord(leftWordAsArticle.getWord().getWord());
            this.leftWord = leftWord;
        }

        if (rightWordAsArticle != null) {
            WordWithId rightWord = new WordWithId();
            rightWord.setId(rightWordAsArticle.getId());
            rightWord.setWord(rightWordAsArticle.getWord().getWord());
            this.rightWord = rightWord;
        }

        this.wordArticle = wordArticle;
    }

    @Data
    public static class WordWithId {
        private String id;
        private String word;
    }
}
