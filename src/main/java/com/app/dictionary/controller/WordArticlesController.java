package com.app.dictionary.controller;

import com.app.dictionary.dto.WordArticleLanguages;
import com.app.dictionary.model.WordArticle;
import com.app.dictionary.service.WordArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class WordArticlesController {

    private final WordArticleService wordArticleService;

    public WordArticlesController(WordArticleService wordArticleService) {
        this.wordArticleService = wordArticleService;
    }

    @PostMapping("/{firstLanguage}/{secondLanguage}")
    void save(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @RequestBody WordArticle wordArticle) {
        wordArticleService.save(wordArticle, new WordArticleLanguages(firstLanguage, secondLanguage));
    }

    @GetMapping("/{firstLanguage}/{secondLanguage}/{id}")
    Optional<WordArticle> findById(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String id) {
        return wordArticleService.findById(id, new WordArticleLanguages(firstLanguage, secondLanguage));
    }

    //todo can languages be obtained from object?
    @PutMapping("/{firstLanguage}/{secondLanguage}/{id}")
    WordArticle update(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String id, @RequestBody WordArticle wordArticle) {
        if (!StringUtils.equals(id, wordArticle.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id didn't match");
        }
        return wordArticleService.update(wordArticle, new WordArticleLanguages(firstLanguage, secondLanguage));
    }

    @GetMapping("/{firstLanguage}/{secondLanguage}/prefix/{wordPrefix}")
    List<WordArticle> findByUkrainianWordStartingWith(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String wordPrefix) {
        return wordArticleService.findByWordStartWith(wordPrefix, new WordArticleLanguages(firstLanguage, secondLanguage));
    }

    @GetMapping("/{firstLanguage}/{secondLanguage}/search/{searchLanguage}/{wordPart}")
    List<WordArticle> findByUkrainianWordContains(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String wordPart, @PathVariable String searchLanguage) {
        //todo check that searchLanguage is first or second language and search by first language words or second language words
        return wordArticleService.findByWordContains(wordPart, new WordArticleLanguages(firstLanguage, secondLanguage));
    }

    @GetMapping("/{firstLanguage}/{secondLanguage}")
    List<WordArticle> findAll(@PathVariable String firstLanguage, @PathVariable String secondLanguage) {
        return wordArticleService.findAll(new WordArticleLanguages(firstLanguage, secondLanguage));
    }

    @DeleteMapping("/{firstLanguage}/{secondLanguage}/{id}")
    public void remove(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String id) {
        wordArticleService.remove(id, new WordArticleLanguages(firstLanguage, secondLanguage));
    }
}
