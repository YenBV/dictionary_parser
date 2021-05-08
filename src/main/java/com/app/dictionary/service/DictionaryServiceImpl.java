package com.app.dictionary.service;

import com.app.dictionary.dao.DictionaryRepository;
import com.app.dictionary.dto.DictionaryDTO;
import com.app.dictionary.model.Dictionary;
import com.app.dictionary.model.GermanWord;
import com.app.dictionary.model.UkrainianWord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final UkrainianWordService ukrainianWordService;
    public final GermanWordService germanWordService;
    private final DictionaryRepository dictionaryRepository;

    public DictionaryServiceImpl(
            UkrainianWordService ukrainianWordService,
            GermanWordService germanWordService,
            DictionaryRepository dictionaryRepository) {
        this.ukrainianWordService = ukrainianWordService;
        this.germanWordService = germanWordService;
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public void save(DictionaryDTO dictionary) {
        Dictionary entity = dictionary.toDictionary();
        dictionaryRepository.save(entity);
    }

    @Override
    public Optional<Dictionary> findById(long id) {
        return dictionaryRepository.findById(id);
    }

    @Transactional
    @Override
    public List<Dictionary> findByUkrainianWordStartingWith(String uaWordPrefix) {
        List<UkrainianWord> ukrWords = ukrainianWordService.findByWordStartingWith(uaWordPrefix);
        return dictionaryRepository.findDictionariesByUkrainianWordsIn(ukrWords);
    }

    @Override
    public List<Dictionary> findByUkrainianWordContains(String wordPart) {
        List<UkrainianWord> ukrWords = ukrainianWordService.findByWordContains(wordPart);
        return dictionaryRepository.findDictionariesByUkrainianWordsIn(ukrWords);
    }

    @Override
    public List<Dictionary> findByGermanWordStartingWith(String germanWordPrefix) {
        List<GermanWord> germanWords = germanWordService.findByWordStartingWith(germanWordPrefix);
        return dictionaryRepository.findDictionariesByGermanWordsIn(germanWords);
    }

    @Override
    public List<Dictionary> findByGermanWordContains(String wordPart) {
        List<GermanWord> germanWords = germanWordService.findByWordContains(wordPart);
        return dictionaryRepository.findDictionariesByGermanWordsIn(germanWords);
    }

    @Override
    public List<DictionaryDTO> findAll() {
        List<Dictionary> dictionaries = dictionaryRepository.findAll();
        return dictionaries
                .stream()
                .map(DictionaryDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Dictionary update(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    @Override
    public void remove(Long id) {
        dictionaryRepository.deleteById(id);
    }
}
