package com.app.dictionary.dto;

import com.app.dictionary.model.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class DictionaryDTO {
    private List<WordDTO> words;

    public DictionaryDTO(List<WordDTO> words) {
        this.words = words;
    }

    public DictionaryDTO() {
    }

    public List<WordDTO> getWords() {
        return words;
    }

    public void setWords(List<WordDTO> words) {
        this.words = words;
    }

    public Dictionary toDictionary() {
        Dictionary dictionary = new Dictionary();
        List<UkrainianWord> ukrainianWords = new ArrayList<>();
        List<GermanWord> germanWords = new ArrayList<>();
        for (WordDTO word : words) {
            Pair<Language, Word> langWord = word.toWord();
            if (langWord.getKey() == Language.UKRAINIAN) {
                ukrainianWords.add((UkrainianWord) langWord.getValue());
            } else {
                germanWords.add((GermanWord) langWord.getValue());
            }
        }
        dictionary.setUkrainianWords(ukrainianWords);
        dictionary.setGermanWords(germanWords);
        return dictionary;
    }
}
