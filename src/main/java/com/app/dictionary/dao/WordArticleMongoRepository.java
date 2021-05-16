package com.app.dictionary.dao;

import com.app.dictionary.model.WordArticle;
import com.app.dictionary.model.WordArticleSearchResult;
import com.app.dictionary.model.WordArticleWithCloseWords;
import com.app.dictionary.util.WordUtils;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;
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
        mongoTemplate.indexOps(collection).ensureIndex(new Index("word.word", Sort.Direction.ASC).unique());
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

    public WordArticleSearchResult findByWordStartWith(String wordPart, String collection, PageRequest pageRequest) {
        if (!mongoTemplate.collectionExists(collection)) {
            return new WordArticleSearchResult(Collections.emptyList(), 0);
        }

        Query query = query(where("word.word").regex(format("^%s.*", wordPart), "i"));
        selectFieldsForSearch(query);

        List<WordArticle> wordArticles = mongoTemplate.find(Query.of(query).with(pageRequest), WordArticle.class, collection);
        long count = mongoTemplate.count(query, collection);
        return new WordArticleSearchResult(wordArticles, count);
    }

    public WordArticleSearchResult findByOtherLanguageWordsStartWith(String prefix, String collection, PageRequest pageRequest) {
        if (!mongoTemplate.collectionExists(collection)) {
            return new WordArticleSearchResult(Collections.emptyList(), 0);
        }

        Query query = query(where("otherLanguageWords.word").regex(format("^%s.*", prefix), "i"));
        List<WordArticle> wordArticles = mongoTemplate.find(Query.of(query).with(pageRequest), WordArticle.class, collection);
        long count = mongoTemplate.count(query, collection);
        return new WordArticleSearchResult(wordArticles, count);
    }

    public WordArticleSearchResult findByWordPart(String wordPart, String collection, PageRequest pageRequest) {
        if (!mongoTemplate.collectionExists(collection)) {
            return new WordArticleSearchResult(Collections.emptyList(), 0);
        }

        String wordForSearch = wordPart.toLowerCase();

        BasicQuery basicQuery = getSearchByWordQuery(wordForSearch);
        basicQuery.setSortObject(Document.parse("{score:{$meta:\"textScore\"}, \"word.word\": 1}"));
        selectFieldsForSearch(basicQuery);

        List<WordArticle> wordArticles = mongoTemplate.find(Query.of(basicQuery).with(pageRequest), WordArticle.class, collection);
        long count = mongoTemplate.count(basicQuery, collection);
        return new WordArticleSearchResult(wordArticles, count);
    }

    public List<WordArticle> findAll(String collection) {
        return mongoTemplate.findAll(WordArticle.class, collection);
    }

    public void deleteById(String id, String collection) {
        mongoTemplate.remove(query(where("_id").is(id)), collection);
    }

    private BasicQuery getSearchByWordQuery(String wordForSearch) {
        String query = format("{$or: [{\"word.word\":{$regex: \".*%s.*\", $options: \"i\"}}, {\"otherLanguageWords.word\":{$regex: \".*%s.*\", $options: \"i\"}}, {$text: {$search: \"%s\"}}]}", wordForSearch, wordForSearch, WordUtils.getNgramsForWord(wordForSearch));
        return new BasicQuery(query);
    }

    private void selectFieldsForSearch(Query basicQuery) {
        basicQuery.fields().slice("word.definitions", 1).slice("otherLanguageWords.definitions", 1);
    }

    public Optional<WordArticleWithCloseWords> findByIdWithCloseWords(String id, String collection) {
        WordArticle wordArticle = mongoTemplate.findById(id, WordArticle.class, collection);
        if (wordArticle == null) {
            return Optional.empty();
        }

        Query queryLeftWord = query(where("word.word").lt(wordArticle.getWord().getWord())).with(Sort.by(Sort.Direction.DESC, "word.word")).limit(1);
        queryLeftWord.fields().include("word.word");
        WordArticle leftWordAsArticle = mongoTemplate.findOne(queryLeftWord, WordArticle.class, collection);

        Query queryRightWord = query(where("word.word").gt(wordArticle.getWord().getWord())).with(Sort.by(Sort.Direction.ASC, "word.word")).limit(1);
        queryRightWord.fields().include("word.word");
        WordArticle rightWordAsArticle = mongoTemplate.findOne(queryRightWord, WordArticle.class, collection);

        WordArticleWithCloseWords word = new WordArticleWithCloseWords(leftWordAsArticle, wordArticle, rightWordAsArticle);

        return Optional.of(word);
    }

    public Optional<String> findWordByExactMatch(String word, String collectionName) {
        Query query = query(where("word.word").regex(format("^%s$", word), "i"));
        Optional<WordArticle> article = Optional.ofNullable(mongoTemplate.findOne(query, WordArticle.class, collectionName));
        return article.map(it -> it.getWord().getWord());
    }
}
