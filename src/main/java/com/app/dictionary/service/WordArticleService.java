package com.app.dictionary.service;

import com.app.dictionary.model.WordArticle;

import java.util.List;
import java.util.Optional;

public interface WordArticleService {

    void save(WordArticle word, String fistLanguage, String secondLanguage);

    Optional<WordArticle> findById(String id, String fistLanguage, String secondLanguage);

    List<WordArticle> findByWordStartWith(String uaWordPrefix, String fistLanguage, String secondLanguage);

    List<WordArticle> findByOtherLanguageWordsStartWith(String germanWordPrefix, String fistLanguage, String secondLanguage);

    List<WordArticle> findAll(String fistLanguage, String secondLanguage);

    WordArticle update(WordArticle wordArticle, String fistLanguage, String secondLanguage);

    void remove(String id, String fistLanguage, String secondLanguage);

    List<WordArticle> findByWordContains(String wordPart, String fistLanguage, String secondLanguage);

    List<WordArticle> findByOtherLanguageWordsContains(String wordPart, String fistLanguage, String secondLanguage);
}
