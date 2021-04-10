package com.app.dictionary.service;

import com.app.dictionary.dao.DictionaryRepository;
import com.app.dictionary.model.Dictionary;
import com.app.dictionary.model.UkrainianWord;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final UkrainianWordService ukrainianWordService;
    private final DictionaryRepository dictionaryRepository;

    public DictionaryServiceImpl(
            UkrainianWordService ukrainianWordService,
            DictionaryRepository dictionaryRepository) {
        this.ukrainianWordService = ukrainianWordService;
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public void save(Dictionary dictionary) {
        dictionaryRepository.save(dictionary);
    }

    @Override
    public Optional<Dictionary> findById(long id) {
        return dictionaryRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Dictionary> findByUkrainianWordStartingWith(String uaWordPrefix) {
        UkrainianWord ukrWord = ukrainianWordService.findByWordStartingWith(uaWordPrefix);
        return dictionaryRepository.findByUkrainianWordsContains(ukrWord);
    }

    @Override
    public Dictionary findByGermanWordStartingWith(String germanWordPrefix) {
        //TODO:2021-03-26:yenbv: impl.
        throw new RuntimeException("Not supported yet");
    }

    @Override
    public List<Dictionary> findAll() {
        return dictionaryRepository.findAll();
    }
}
