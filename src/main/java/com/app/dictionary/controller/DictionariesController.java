package com.app.dictionary.controller;

import com.app.dictionary.dto.DictionaryDTO;
import com.app.dictionary.dto.WordDTO;
import com.app.dictionary.model.Dictionary;
import com.app.dictionary.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DictionariesController {

    private final DictionaryService dictionaryService;

    public DictionariesController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @PostMapping("/{firstLanguage}/{secondLanguage}")
    void save(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @RequestBody List<WordDTO> words) {
        dictionaryService.save(new DictionaryDTO(words), firstLanguage, secondLanguage);
    }

    @GetMapping("/{firstLanguage}/{secondLanguage}/{id}")
    Optional<DictionaryDTO> findById(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String id) {
        return dictionaryService.findById(id, firstLanguage, secondLanguage).map(DictionaryDTO::from);
    }

    //todo validate
    //todo can languages be obtained from object?
    @PutMapping("/{firstLanguage}/{secondLanguage}/{id}")
    DictionaryDTO update(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String id, @RequestBody List<WordDTO> words) {
        DictionaryDTO dictionaryDTO = new DictionaryDTO(words);
        dictionaryDTO.setId(id);
        return DictionaryDTO.from(dictionaryService.update(dictionaryDTO.toDictionary(), firstLanguage, secondLanguage));
    }

    //todo replace hardcoded value with parameter
    @GetMapping("/{firstLanguage}/{secondLanguage}/prefix/{wordPrefix}")
    List<DictionaryDTO> findByUkrainianWordStartingWith(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String wordPrefix) {
        List<Dictionary> byPrefix = dictionaryService.findByUkrainianWordStartingWith(wordPrefix, firstLanguage, secondLanguage);
        return byPrefix.stream().map(DictionaryDTO::from).collect(Collectors.toList());
    }

    //todo replace hardcoded value with parameter
    @GetMapping("/{firstLanguage}/{secondLanguage}/search/{searchLanguage}/{wordPart}")
    List<DictionaryDTO> findByUkrainianWordContains(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String wordPart, @PathVariable String searchLanguage) {
        //todo check that searchLanguage is first or second language and search by first language words or second language words
        List<Dictionary> byPrefix = dictionaryService.findByUkrainianWordContains(wordPart, firstLanguage, secondLanguage);
        return byPrefix.stream().map(DictionaryDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/{firstLanguage}/{secondLanguage}")
    List<DictionaryDTO> findAll(@PathVariable String firstLanguage, @PathVariable String secondLanguage) {
        return dictionaryService.findAll(firstLanguage, secondLanguage);
    }

    @DeleteMapping("/{firstLanguage}/{secondLanguage}/{id}")
    public void remove(@PathVariable String firstLanguage, @PathVariable String secondLanguage, @PathVariable String id) {
        dictionaryService.remove(id, firstLanguage, secondLanguage);
    }
}
