package com.app.dictionary.dao;

import com.app.dictionary.model.UkrainianWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UkrainianWordRepository extends JpaRepository<UkrainianWord, Long> {

    List<UkrainianWord> findByWordStartingWith(String wordPrefix);

    List<UkrainianWord> findByWordContainsIgnoreCase(String wordPart);
}
