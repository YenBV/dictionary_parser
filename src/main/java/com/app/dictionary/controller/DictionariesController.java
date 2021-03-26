package com.app.dictionary.controller;

import com.app.dictionary.model.Dictionary;
import com.app.dictionary.service.DictionaryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/dictionaries")
public class DictionariesController {

    private final DictionaryService dictionaryService;

    public DictionariesController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @PostMapping("/")
    void save(@RequestBody Dictionary dictionary) {
        dictionaryService.save(dictionary);
    }

    @GetMapping("/{id}")
    Optional<Dictionary> findById(@PathVariable long id){
        return dictionaryService.findById(id);
    }

    @GetMapping("/ua/{wordPrefix}")
    Optional<Dictionary> findByUkrainianWordStartingWith(@PathVariable String wordPrefix){
        System.out.println(wordPrefix);
        System.out.println("================================================================");
        return dictionaryService.findByUkrainianWordStartingWith(wordPrefix);
    }

    @GetMapping("/de/{wordPrefix}")
    Dictionary findByGermanWordStartingWith(@PathVariable String wordPrefix){
        return dictionaryService.findByGermanWordStartingWith(wordPrefix);
    }
}
