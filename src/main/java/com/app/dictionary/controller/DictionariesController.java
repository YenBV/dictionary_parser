package com.app.dictionary.controller;

import com.app.dictionary.dto.DictionaryDTO;
import com.app.dictionary.dto.WordDTO;
import com.app.dictionary.model.Dictionary;
import com.app.dictionary.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dictionaries")
public class DictionariesController {

    private final DictionaryService dictionaryService;

    public DictionariesController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @PostMapping("/")
    void save(@RequestBody List<WordDTO> words) {
        dictionaryService.save(new DictionaryDTO(words));
    }

    @GetMapping("/{id}")
    Optional<Dictionary> findById(@PathVariable long id) {
        return dictionaryService.findById(id);
    }

//    @GetMapping("/ua/{wordPrefix}")
//    List<Dictionary> findByUkrainianWordStartingWith(@PathVariable String wordPrefix) {
//        return dictionaryService.findByUkrainianWordStartingWith(wordPrefix);
//    }
//
//    @GetMapping("/de/{wordPrefix}")
//    List<Dictionary> findByGermanWordStartingWith(@PathVariable String wordPrefix) {
//        return dictionaryService.findByGermanWordStartingWith(wordPrefix);
//    }

    @GetMapping("/")
    List<Dictionary> findAll() {
        return dictionaryService.findAll();
    }
}
