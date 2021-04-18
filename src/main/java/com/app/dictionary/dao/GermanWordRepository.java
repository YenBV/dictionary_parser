package com.app.dictionary.dao;

import com.app.dictionary.model.GermanWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GermanWordRepository extends JpaRepository<GermanWord, Long> {

    List<GermanWord> findByWordStartingWith(String wordPrefix);
}
