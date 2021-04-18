package com.app.dictionary.service;

import com.app.dictionary.model.GermanWord;

import java.util.List;
import java.util.Optional;

public interface GermanWordService {

    Optional<GermanWord> findById(long id);

    List<GermanWord> findByWordStartingWith(String prefix);
}
