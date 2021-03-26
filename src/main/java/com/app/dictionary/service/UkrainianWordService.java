package com.app.dictionary.service;

import com.app.dictionary.model.UkrainianWord;

import java.util.Optional;

public interface UkrainianWordService {

    Optional<UkrainianWord> findById(long id);

    UkrainianWord findByWordStartingWith(String word);
}
