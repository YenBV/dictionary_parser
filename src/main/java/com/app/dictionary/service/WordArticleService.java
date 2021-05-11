package com.app.dictionary.service;

import com.app.dictionary.dto.WordArticleLanguages;
import com.app.dictionary.model.WordArticle;

import java.util.List;
import java.util.Optional;

public interface WordArticleService {

    void save(WordArticle word, WordArticleLanguages languages);

    Optional<WordArticle> findById(String id, WordArticleLanguages languages);

    List<WordArticle> findByWordStartWith(String uaWordPrefix, WordArticleLanguages languages);

    List<WordArticle> findByOtherLanguageWordsStartWith(String germanWordPrefix, WordArticleLanguages languages);

    List<WordArticle> findAll(WordArticleLanguages languages);

    WordArticle update(WordArticle wordArticle, WordArticleLanguages languages);

    void remove(String id, WordArticleLanguages languages);

    List<WordArticle> findByWordContains(String wordPart, WordArticleLanguages languages);

    List<WordArticle> findByOtherLanguageWordsContains(String wordPart, WordArticleLanguages languages);

    List<WordArticle> findByWordPart(WordArticleLanguages languages, String wordPart);
}
