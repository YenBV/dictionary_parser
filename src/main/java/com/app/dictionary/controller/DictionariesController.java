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
    Optional<DictionaryDTO> findById(@PathVariable long id) {
        return dictionaryService.findById(id).map(DictionaryDTO::from);
    }

    //todo validate
    @PutMapping("/{id}")
    DictionaryDTO update(@PathVariable long id, @RequestBody List<WordDTO> words) {
        DictionaryDTO dictionaryDTO = new DictionaryDTO(words);
        dictionaryDTO.setId(id);
        return DictionaryDTO.from(dictionaryService.update(dictionaryDTO.toDictionary()));
    }

    //todo replace hardcoded value with parameter
    @GetMapping("/ukrainian/{wordPrefix}")
    List<DictionaryDTO> findByUkrainianWordStartingWith(@PathVariable String wordPrefix) {
        List<Dictionary> byPrefix = dictionaryService.findByUkrainianWordStartingWith(wordPrefix);
        return byPrefix.stream().map(DictionaryDTO::from).collect(Collectors.toList());
    }

    //todo replace hardcoded value with parameter
    @GetMapping("/german/{wordPrefix}")
    List<DictionaryDTO> findByGermanWordStartingWith(@PathVariable String wordPrefix) {
        List<Dictionary> byPrefix = dictionaryService.findByGermanWordStartingWith(wordPrefix);
        return byPrefix.stream().map(DictionaryDTO::from).collect(Collectors.toList());
    }

    //todo replace hardcoded value with parameter
    @GetMapping("/ukrainian/search/{wordPart}")
    List<DictionaryDTO> findByUkrainianWordContains(@PathVariable String wordPart) {
        List<Dictionary> byPrefix = dictionaryService.findByUkrainianWordContains(wordPart);
        return byPrefix.stream().map(DictionaryDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/")
    List<DictionaryDTO> findAll() {
        return dictionaryService.findAll();
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        dictionaryService.remove(id);
    }
}
