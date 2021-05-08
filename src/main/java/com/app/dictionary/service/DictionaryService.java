package com.app.dictionary.service;

import com.app.dictionary.dto.DictionaryDTO;
import com.app.dictionary.model.Dictionary;

import java.util.List;
import java.util.Optional;

public interface DictionaryService {

    void save(DictionaryDTO word, String fistLanguage, String secondLanguage);

    Optional<Dictionary> findById(String id, String fistLanguage, String secondLanguage);

    List<Dictionary> findByUkrainianWordStartingWith(String uaWordPrefix, String fistLanguage, String secondLanguage);

    List<Dictionary> findByGermanWordStartingWith(String germanWordPrefix, String fistLanguage, String secondLanguage);

    List<DictionaryDTO> findAll(String fistLanguage, String secondLanguage);

    Dictionary update(Dictionary dictionary, String fistLanguage, String secondLanguage);

    void remove(String id, String fistLanguage, String secondLanguage);

    List<Dictionary> findByUkrainianWordContains(String wordPart, String fistLanguage, String secondLanguage);

    List<Dictionary> findByGermanWordContains(String wordPart, String fistLanguage, String secondLanguage);
}
