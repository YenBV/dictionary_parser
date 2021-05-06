package com.app.dictionary.service;

import com.app.dictionary.dao.GermanWordRepository;
import com.app.dictionary.model.GermanWord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GermanWordServiceImpl implements GermanWordService {

    private final GermanWordRepository germanWordRepository;

    public GermanWordServiceImpl(GermanWordRepository germanWordRepository) {
        this.germanWordRepository = germanWordRepository;
    }

    @Override
    public Optional<GermanWord> findById(long id) {
        return germanWordRepository.findById(id);
    }

    @Override
    public List<GermanWord> findByWordStartingWith(String prefix) {
        return germanWordRepository.findByWordStartingWith(prefix);
    }

    @Override
    public List<GermanWord> findByWordContains(String wordPart) {
        return germanWordRepository.findByWordContainsIgnoreCase(wordPart);
    }
}
