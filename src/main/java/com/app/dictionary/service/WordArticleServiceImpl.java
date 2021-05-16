package com.app.dictionary.service;

import com.app.dictionary.dao.WordArticleMongoRepository;
import com.app.dictionary.dto.UniqueWordResponse;
import com.app.dictionary.dto.WordArticleLanguages;
import com.app.dictionary.model.WordArticle;
import com.app.dictionary.model.WordArticleSearchResult;
import com.app.dictionary.model.WordArticleWithCloseWords;
import com.app.dictionary.util.MongoCollectionUtils;
import com.app.dictionary.util.WordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordArticleServiceImpl implements WordArticleService {

    private final WordArticleMongoRepository wordArticleMongoRepository;

    @Override
    public void save(WordArticle wordArticle, WordArticleLanguages languages) {
        String collection = MongoCollectionUtils.toCollectionName(languages);

        wordArticle.getWord().setNgrams(WordUtils.getNgramsForWord(wordArticle.getWord().getWord()));
        wordArticle.getOtherLanguageWords().forEach(it -> it.setNgrams(WordUtils.getNgramsForWord(it.getWord())));

        wordArticleMongoRepository.save(wordArticle, collection);
    }

    @Override
    public Optional<WordArticle> findById(String id, WordArticleLanguages languages) {
        String collection = MongoCollectionUtils.toCollectionName(languages);
        return wordArticleMongoRepository.findById(id, collection);
    }

    @Override
    public WordArticleSearchResult findByWordStartWith(String uaWordPrefix, WordArticleLanguages languages, int pageSize, int pageNumber) {
        String collection = MongoCollectionUtils.toCollectionName(languages);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "word.word"));
        return wordArticleMongoRepository.findByWordStartWith(uaWordPrefix, collection, pageRequest);
    }

    @Override
    public WordArticleSearchResult findByOtherLanguageWordsStartWith(String germanWordPrefix, WordArticleLanguages languages, int pageSize, int pageNumber) {
        String collection = MongoCollectionUtils.toCollectionName(languages);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "word.word"));
        return wordArticleMongoRepository.findByOtherLanguageWordsStartWith(germanWordPrefix, collection, pageRequest);
    }

    @Override
    public WordArticleSearchResult findByWordPart(WordArticleLanguages languages, String wordPart, int pageSize, int pageNumber) {
        String collection = MongoCollectionUtils.toCollectionName(languages);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return wordArticleMongoRepository.findByWordPart(wordPart, collection, pageRequest);
    }

    @Override
    public Optional<WordArticleWithCloseWords> findByIdWithCloseWords(String id, WordArticleLanguages articleLanguages) {
        String collection = MongoCollectionUtils.toCollectionName(articleLanguages);
        return wordArticleMongoRepository.findByIdWithCloseWords(id, collection);
    }

    @Override
    public Optional<UniqueWordResponse> findWordByExactMatch(String word, WordArticleLanguages wordArticleLanguages) {
        String collectionName = MongoCollectionUtils.toCollectionName(wordArticleLanguages);
        return wordArticleMongoRepository.findWordByExactMatch(word, collectionName).map(UniqueWordResponse::new);
    }

    @Override
    public List<WordArticle> findAll(WordArticleLanguages languages) {
        String collection = MongoCollectionUtils.toCollectionName(languages);
        return wordArticleMongoRepository.findAll(collection);
    }

    @Override
    public WordArticle update(WordArticle wordArticle, WordArticleLanguages languages) {
        String collection = MongoCollectionUtils.toCollectionName(languages);

        wordArticle.getWord().setNgrams(WordUtils.getNgramsForWord(wordArticle.getWord().getWord().toLowerCase()));
        wordArticle.getOtherLanguageWords().forEach(it -> it.setNgrams(WordUtils.getNgramsForWord(it.getWord().toLowerCase())));

        return wordArticleMongoRepository.save(wordArticle, collection);
    }

    @Override
    public void remove(String id, WordArticleLanguages languages) {
        String collection = MongoCollectionUtils.toCollectionName(languages);
        wordArticleMongoRepository.deleteById(id, collection);
    }
}
