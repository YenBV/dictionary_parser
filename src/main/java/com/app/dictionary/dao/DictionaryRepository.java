package com.app.dictionary.dao;

import com.app.dictionary.model.Dictionary;
import com.app.dictionary.model.UkrainianWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    List<Dictionary> findByUkrainianWordsContains(UkrainianWord word);
}