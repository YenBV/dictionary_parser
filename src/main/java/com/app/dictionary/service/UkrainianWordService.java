package com.app.dictionary.service;

import com.app.dictionary.model.UkrainianWord;

import java.util.List;
import java.util.Optional;

public interface UkrainianWordService {

    Optional<UkrainianWord> findById(long id);

    List<UkrainianWord> findByWordStartingWith(String word);

    List<UkrainianWord> findByWordContains(String wordPart);
}
