package com.app.dictionary.service.parser;

import com.app.dictionary.model.Word;

import java.util.List;
import java.util.stream.Collectors;

public class WordsParser {

    private final List<String> words;

    public WordsParser(List<String> words) {
        this.words = words;
    }

    public List<Word> parseWords(WordParser wordParser) {
        return words
                .stream()
                .map(wordParser::parse)
                .collect(Collectors.toList());
    }
}
