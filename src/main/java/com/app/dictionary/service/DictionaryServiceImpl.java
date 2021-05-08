package com.app.dictionary.service;

import com.app.dictionary.dao.DictionaryMongoRepository;
import com.app.dictionary.dto.DictionaryDTO;
import com.app.dictionary.model.Dictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    //    private final UkrainianWordService ukrainianWordService;
//    public final GermanWordService germanWordService;
//    private final DictionaryRepository dictionaryRepository;
    private final DictionaryMongoRepository dictionaryMongoRepository;

    @Override
    public void save(DictionaryDTO dictionary, String firstLanguage, String secondLanguage) {
        Dictionary entity = dictionary.toDictionary();
//        dictionaryRepository.save(entity);
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);

        dictionaryMongoRepository.save(entity, collection);
    }

    @Override
    public Optional<Dictionary> findById(String id, String firstLanguage, String secondLanguage) {
//        return dictionaryRepository.findById(id);
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        return dictionaryMongoRepository.findById(id, collection);
    }

    //    @Transactional
    @Override
    public List<Dictionary> findByUkrainianWordStartingWith(String uaWordPrefix, String firstLanguage, String secondLanguage) {
//        List<UkrainianWord> ukrWords = ukrainianWordService.findByWordStartingWith(uaWordPrefix);
//        return dictionaryRepository.findDictionariesByUkrainianWordsIn(ukrWords);
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);

        return dictionaryMongoRepository.findByFirstWordsStartWith(uaWordPrefix, collection);
    }

    @Override
    public List<Dictionary> findByUkrainianWordContains(String wordPart, String firstLanguage, String secondLanguage) {
//        List<UkrainianWord> ukrWords = ukrainianWordService.findByWordContains(wordPart);
//        return dictionaryRepository.findDictionariesByUkrainianWordsIn(ukrWords);
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);

        return dictionaryMongoRepository.findByFirstWordsContain(wordPart, collection);
    }

    @Override
    public List<Dictionary> findByGermanWordStartingWith(String germanWordPrefix, String firstLanguage, String secondLanguage) {
//        List<GermanWord> germanWords = germanWordService.findByWordStartingWith(germanWordPrefix);
//        return dictionaryRepository.findDictionariesByGermanWordsIn(germanWords);
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);

        return dictionaryMongoRepository.findDictionariesSecondWordsStartWith(germanWordPrefix, collection);
    }

    @Override
    public List<Dictionary> findByGermanWordContains(String wordPart, String firstLanguage, String secondLanguage) {
//        List<GermanWord> germanWords = germanWordService.findByWordContains(wordPart);
//        return dictionaryRepository.findDictionariesByGermanWordsIn(germanWords);
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);

        return dictionaryMongoRepository.findDictionariesSecondWordsContains(wordPart, collection);
    }

    @Override
    public List<DictionaryDTO> findAll(String firstLanguage, String secondLanguage) {
//        List<Dictionary> dictionaries = dictionaryRepository.findAll();
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);

        List<Dictionary> dictionaries = dictionaryMongoRepository.findAll(collection);
        return dictionaries
                .stream()
                .map(DictionaryDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Dictionary update(Dictionary dictionary, String firstLanguage, String secondLanguage) {
//        return dictionaryRepository.save(dictionary);
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        return dictionaryMongoRepository.save(dictionary, collection);
    }

    @Override
    public void remove(String id, String firstLanguage, String secondLanguage) {
//        dictionaryRepository.deleteById(id);
        String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);
        dictionaryMongoRepository.deleteById(id, collection);
    }
}
