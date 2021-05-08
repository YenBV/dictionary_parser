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
    List<WordArticle> findByWordStartingWith(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String wordPrefix) {
        return wordArticleService.findByWordStartWith(wordPrefix, new WordArticleLanguages(firstLanguage, secondLanguage));
    }

    @GetMapping("/{firstLanguage}/{secondLanguage}/search/{searchLanguage}/{wordPart}")
    List<WordArticle> findByWordsContain(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String wordPart, @PathVariable String searchLanguage) {
        WordArticleLanguages languages = new WordArticleLanguages(firstLanguage, secondLanguage);
        if (searchLanguage.equalsIgnoreCase(firstLanguage)) {
            return wordArticleService.findByWordContains(wordPart, languages);
        } else if (searchLanguage.equalsIgnoreCase(secondLanguage)) {
            return wordArticleService.findByOtherLanguageWordsContains(wordPart, languages);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't determine querying language.");
        }
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
