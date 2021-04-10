package com.app.dictionary.service;

import com.app.dictionary.model.Dictionary;

import java.util.List;
import java.util.Optional;

public interface DictionaryService {

    void save(Dictionary word);

    Optional<Dictionary> findById(long id);

//    List<Dictionary> findByUkrainianWordStartingWith(String uaWordPrefix);
//
//    List<Dictionary> findByGermanWordStartingWith(String germanWordPrefix);

    List<Dictionary> findAll();
}
