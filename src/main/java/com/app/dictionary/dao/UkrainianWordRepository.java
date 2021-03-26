package com.app.dictionary.dao;

import com.app.dictionary.model.UkrainianWord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UkrainianWordRepository extends JpaRepository<UkrainianWord, Long> {

    UkrainianWord findByWordStartingWith(String wordPrefix);
}
