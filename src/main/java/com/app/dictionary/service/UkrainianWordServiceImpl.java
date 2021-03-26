package com.app.dictionary.service;

import com.app.dictionary.dao.UkrainianWordRepository;
import com.app.dictionary.model.UkrainianWord;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UkrainianWordServiceImpl implements UkrainianWordService {

    private final UkrainianWordRepository ukrainianWordRepository;

    public UkrainianWordServiceImpl(UkrainianWordRepository ukrainianWordRepository) {
        this.ukrainianWordRepository = ukrainianWordRepository;
    }

    @Override
    public Optional<UkrainianWord> findById(long id) {
        return ukrainianWordRepository.findById(id);
    }

    @Override
    public UkrainianWord findByWordStartingWith(String wordPrefix) {
        return ukrainianWordRepository.findByWordStartingWith(wordPrefix);
    }
}
