package com.app.dictionary.dao;

import com.app.dictionary.model.Dictionary;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
@RequiredArgsConstructor
public class DictionaryMongoRepository {
    private final MongoTemplate mongoTemplate;

    public Dictionary save(Dictionary multiLanguageWord, String collection) {
        return mongoTemplate.save(multiLanguageWord, collection);
    }

    public Optional<Dictionary> findById(String id, String collection) {
        return Optional.ofNullable(mongoTemplate.findById(id, Dictionary.class, collection));
    }

    public List<Dictionary> findByFirstWordsContain(String prefix, String collection) {
        //todo replace hardcode
        return mongoTemplate.find(query(where("ukrainianWords.word").regex(format("%s.*", prefix))), Dictionary.class, collection);
    }

    public List<Dictionary> findByFirstWordsStartWith(String wordPart, String collection) {
        return mongoTemplate.find(query(where("ukrainianWords.word").regex(format(".*%s.*", wordPart))), Dictionary.class, collection);
    }

    public List<Dictionary> findDictionariesSecondWordsStartWith(String prefix, String collection) {
        //todo replace duplicate
        return mongoTemplate.find(query(where("germanWords.word").regex(format("%s.*", prefix))), Dictionary.class, collection);
    }

    public List<Dictionary> findDictionariesSecondWordsContains(String wordPart, String collection) {
        //todo replace duplicate

        return mongoTemplate.find(query(where("germanWords.word").regex(format(".*%s.*", wordPart))), Dictionary.class, collection);
    }

    public List<Dictionary> findAll(String collection) {
        return mongoTemplate.findAll(Dictionary.class, collection);
    }

    public void deleteById(String id, String collection) {
        DeleteResult deleteResult = mongoTemplate.remove(query(where("_id").is(id)), collection);
    }
}
