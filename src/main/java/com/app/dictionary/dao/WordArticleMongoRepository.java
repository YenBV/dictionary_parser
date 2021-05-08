package com.app.dictionary.dao;

import com.app.dictionary.model.WordArticle;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
@RequiredArgsConstructor
public class WordArticleMongoRepository {
    private final MongoTemplate mongoTemplate;

    public WordArticle save(WordArticle multiLanguageWord, String collection) {
        mongoTemplate.indexOps(collection).ensureIndex(new Index("word.word", Sort.Direction.ASC));
        mongoTemplate.indexOps(collection).ensureIndex(new Index("otherLanguageWords.word", Sort.Direction.ASC));
        return mongoTemplate.save(multiLanguageWord, collection);
    }

    public Optional<WordArticle> findById(String id, String collection) {
        return Optional.ofNullable(mongoTemplate.findById(id, WordArticle.class, collection));
    }

    public List<WordArticle> findByWordContains(String prefix, String collection) {
        //todo replace hardcode
        return mongoTemplate.find(query(where("word.word").regex(format(".*%s.*", prefix), "i")), WordArticle.class, collection);
    }

    public List<WordArticle> findByWordStartWith(String wordPart, String collection) {
        return mongoTemplate.find(query(where("word.word").regex(format("%s.*", wordPart), "i")), WordArticle.class, collection);
    }

    public List<WordArticle> findByOtherLanguageWordsStartWith(String prefix, String collection) {
        //todo replace duplicate
        return mongoTemplate.find(query(where("otherLanguageWords.word").regex(format("%s.*", prefix), "i")), WordArticle.class, collection);
    }

    public List<WordArticle> findByOtherLanguageWordsContains(String wordPart, String collection) {
        //todo replace duplicate

        return mongoTemplate.find(query(where("otherLanguageWords.word").regex(format(".*%s.*", wordPart), "i")), WordArticle.class, collection);
    }

    public List<WordArticle> findAll(String collection) {
        return mongoTemplate.findAll(WordArticle.class, collection);
    }

    public void deleteById(String id, String collection) {
        mongoTemplate.remove(query(where("_id").is(id)), collection);
    }
}
