package com.app.dictionary.service;

import com.app.dictionary.dao.WordArticleMongoRepository;
import com.app.dictionary.model.WordArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordArticleServiceImpl implements WordArticleService {

    private final WordArticleMongoRepository wordArticleMongoRepository;

    @Override
    public void save(WordArticle wordArticle, String firstLanguage, String secondLanguage) {
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        wordArticleMongoRepository.save(wordArticle, collection);
    }

    @Override
    public Optional<WordArticle> findById(String id, String firstLanguage, String secondLanguage) {
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        return wordArticleMongoRepository.findById(id, collection);
    }

    @Override
    public List<WordArticle> findByWordStartWith(String uaWordPrefix, String firstLanguage, String secondLanguage) {
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        return wordArticleMongoRepository.findByWordStartWith(uaWordPrefix, collection);
    }

    @Override
    public List<WordArticle> findByWordContains(String wordPart, String firstLanguage, String secondLanguage) {
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        return wordArticleMongoRepository.findByWordContains(wordPart, collection);
    }

    @Override
    public List<WordArticle> findByOtherLanguageWordsStartWith(String germanWordPrefix, String firstLanguage, String secondLanguage) {
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        return wordArticleMongoRepository.findByOtherLanguageWordsStartWith(germanWordPrefix, collection);
    }

    @Override
    public List<WordArticle> findByOtherLanguageWordsContains(String wordPart, String firstLanguage, String secondLanguage) {
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        return wordArticleMongoRepository.findByOtherLanguageWordsContains(wordPart, collection);
    }

    @Override
    public List<WordArticle> findAll(String firstLanguage, String secondLanguage) {
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        return wordArticleMongoRepository.findAll(collection);
    }

    @Override
    public WordArticle update(WordArticle wordArticle, String firstLanguage, String secondLanguage) {
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        return wordArticleMongoRepository.save(wordArticle, collection);
    }

    @Override
    public void remove(String id, String firstLanguage, String secondLanguage) {
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        wordArticleMongoRepository.deleteById(id, collection);
    }
}
