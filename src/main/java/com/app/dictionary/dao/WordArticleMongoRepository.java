package com.app.dictionary.dao;

import com.app.dictionary.model.WordArticle;
import com.app.dictionary.util.WordUtils;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
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

        TextIndexDefinition textIndexDefinition = new TextIndexDefinition.TextIndexDefinitionBuilder()
                .onField("word.ngrams")
                .onField("otherLanguageWords.ngrams")
                .withDefaultLanguage("none")
                .withLanguageOverride("text_language")
                .build();
        mongoTemplate.indexOps(collection).ensureIndex(textIndexDefinition);

        return mongoTemplate.save(multiLanguageWord, collection);
    }

    public Optional<WordArticle> findById(String id, String collection) {
        return Optional.ofNullable(mongoTemplate.findById(id, WordArticle.class, collection));
    }

    public List<WordArticle> findByWordContains(String prefix, String collection) {
        return mongoTemplate.find(query(where("word.word").regex(format(".*%s.*", prefix), "i")), WordArticle.class, collection);
    }

    public List<WordArticle> findByWordStartWith(String wordPart, String collection) {
        Query query = query(where("word.word").regex(format("%s.*", wordPart), "i"));
        selectFieldsForSearch(query);

        return mongoTemplate.find(query, WordArticle.class, collection);
    }

    public List<WordArticle> findByOtherLanguageWordsStartWith(String prefix, String collection) {
        return mongoTemplate.find(query(where("otherLanguageWords.word").regex(format("%s.*", prefix), "i")), WordArticle.class, collection);
    }

    public List<WordArticle> findByOtherLanguageWordsContains(String wordPart, String collection) {
        return mongoTemplate.find(query(where("otherLanguageWords.word").regex(format(".*%s.*", wordPart), "i")), WordArticle.class, collection);
    }

    public List<WordArticle> findAll(String collection) {
        return mongoTemplate.findAll(WordArticle.class, collection);
    }

    public void deleteById(String id, String collection) {
        mongoTemplate.remove(query(where("_id").is(id)), collection);
    }

    public List<WordArticle> findByWordPart(String wordPart, String collection) {
        String wordForSearch = wordPart.toLowerCase();

        String query = format("{$or: [{\"word.word\":{$regex: \".*%s.*\", $options: \"i\"}}, {\"otherLanguageWords.word\":{$regex: \".*%s.*\", $options: \"i\"}}, {$text: {$search: \"%s\"}}]}", wordForSearch, wordForSearch, WordUtils.getNgramsForWord(wordForSearch));
        BasicQuery basicQuery = new BasicQuery(query);
        basicQuery.setSortObject(Document.parse("{score:{$meta:\"textScore\"}}"));
        selectFieldsForSearch(basicQuery);

        return mongoTemplate.find(basicQuery, WordArticle.class, collection);
    }

    private void selectFieldsForSearch(Query basicQuery) {
        basicQuery.fields().slice("word.definitions", 1).slice("otherLanguageWords.definitions", 1);
    }
}
