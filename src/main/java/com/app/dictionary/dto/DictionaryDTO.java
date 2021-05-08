package com.app.dictionary.dto;

import com.app.dictionary.model.*;
import lombok.Data;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.dictionary.model.Language.*;

@Data
public class DictionaryDTO {

    private String id;
    private List<WordDTO> words;

    public DictionaryDTO(List<WordDTO> words) {
        this.words = words;
    }

    public DictionaryDTO() {
    }

    public Dictionary toDictionary() {
        Dictionary dictionary = new Dictionary();
        List<UkrainianWord> ukrainianWords = new ArrayList<>();
        List<GermanWord> germanWords = new ArrayList<>();
        for (WordDTO word : words) {
            if (determineLanguage(word.getLanguage()) == Language.UKRAINIAN) {
                UkrainianWord ukrainianWord = new UkrainianWord();
                copyToWord(ukrainianWord, word);
                ukrainianWord.setLanguage(UKRAINIAN.value());
                ukrainianWords.add(ukrainianWord);
            } else {
                GermanWord germanWord = new GermanWord();
                copyToWord(germanWord, word);
                germanWord.setLanguage(GERMANY.value());
                germanWords.add(germanWord);
            }
        }
        dictionary.setUkrainianWords(ukrainianWords);
        dictionary.setGermanWords(germanWords);
        dictionary.setId(getId());
        return dictionary;
    }

    public static DictionaryDTO from(Dictionary dictionary) {
        List<WordDTO> urkWords = dictionary.getUkrainianWords()
                .stream()
                .map(ukr -> {
                    WordDTO wordDTO = toDto(ukr);
                    wordDTO.setLanguage(Language.UKRAINIAN.value());
                    return wordDTO;
                }).collect(Collectors.toList());
        List<WordDTO> germanWords = dictionary.getGermanWords()
                .stream()
                .map(german -> {
                    WordDTO wordDTO = toDto(german);
                    wordDTO.setLanguage(Language.GERMANY.value());
                    return wordDTO;
                }).collect(Collectors.toList());
        List<WordDTO> words = ListUtils.union(urkWords, germanWords);
        DictionaryDTO dictionaryDTO = new DictionaryDTO(words);
        dictionaryDTO.setId(dictionary.getId());
        return dictionaryDTO;
    }

    private static WordDTO toDto(Word word) {
        WordDTO wordDTO = new WordDTO();
        wordDTO.setWord(word.getWord());
        wordDTO.setDefinitions(word.getDefinitions());
        wordDTO.setImportant(word.isImportant());
        wordDTO.setMorphologyCategory(word.getMorphologyCategory());
        wordDTO.setMorphologyEndings(word.getMorphologyEndings());
        return wordDTO;
    }

    private static void copyToWord(Word entity, WordDTO dto) {
        entity.setWord(dto.getWord());
        entity.setMorphologyEndings(dto.getMorphologyEndings());
        entity.setMorphologyCategory(dto.getMorphologyCategory());
        entity.setDefinitions(dto.getDefinitions());
        entity.setImportant(dto.isImportant());
    }
}
