package com.app.dictionary.service;

import com.app.dictionary.model.Word;

import java.util.List;
import java.util.stream.Collectors;

public class MultiLanguageWordDefinition {

    private final List<String> wordDefinitions;

    public MultiLanguageWordDefinition(List<String> words) {
        this.wordDefinitions = words;
    }

    public List<Word> parseWords(WordParser wordParser) {
        return wordDefinitions
                .stream()
                .map(wordParser::parse)
                .collect(Collectors.toList());
    }
}
