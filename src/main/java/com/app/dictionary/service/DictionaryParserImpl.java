package com.app.dictionary.service;

import com.app.dictionary.model.Dictionary;
import com.app.dictionary.model.Word;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class DictionaryParserImpl implements DictionaryParser {

    private final WordParser wordParser;

    public DictionaryParserImpl(WordParser wordParser) {
        this.wordParser = wordParser;
    }

    @Override
    public Dictionary parse(String content) {
        Dictionary dictionary = new Dictionary();
        List<MultiLanguageWordDefinition> dictionaryDefinition = dictionaryDefinition(content);
        for (MultiLanguageWordDefinition wordDefinitions : dictionaryDefinition) {
            List<Word> words = wordDefinitions.parseWords(wordParser);
            for (Word word : words) {
                word.addMeanings(multiLanguageWordsBesidesCurrent(words, word));
                dictionary.add(word);
            }
        }
        return dictionary;
    }

    private List<Word> multiLanguageWordsBesidesCurrent(List<Word> words, Word current) {
        ArrayList<Word> meanings = new ArrayList<>(words);
        meanings.remove(current);
        return meanings;
    }

    private List<MultiLanguageWordDefinition> dictionaryDefinition(String content) {
        List<MultiLanguageWordDefinition> result = new ArrayList<>();
        List<String> wordDefinitions = new ArrayList<>();
        StringBuilder wordDefBuilder = new StringBuilder();

        String[] lines = content.trim().split("\n");
        for (String line : lines) {
            String pureLine = line.trim();
            if (Pattern.compile("⁕([⁕ ]+)⁕").matcher(pureLine).matches()) {
                if (!wordDefinitions.isEmpty()) {
                    result.add(new MultiLanguageWordDefinition(wordDefinitions));
                    wordDefinitions = new ArrayList<>();
                }
                continue;
            }
            if (Pattern.compile("^(! )?(\\S+), (-?)([а-я]|[a-z]){1,4}(, [а-я]|[a-z])?(.*):$").matcher(pureLine).matches()) {
                String wordDef = wordDefBuilder.toString();
                if (StringUtils.isNotBlank(wordDef)) {
                    wordDefinitions.add(wordDef);
                }
                wordDefBuilder = new StringBuilder();
            }
            wordDefBuilder.append(pureLine);
        }
        wordDefinitions.add(wordDefBuilder.toString());
        result.add(new MultiLanguageWordDefinition(wordDefinitions));
        return result;
    }
}
