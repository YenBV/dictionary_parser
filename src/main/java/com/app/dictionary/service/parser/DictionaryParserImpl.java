package com.app.dictionary.service.parser;

import com.app.dictionary.dao.DictionaryMongoRepository;
import com.app.dictionary.model.Dictionary;
import com.app.dictionary.model.UkrainianWord;
import com.app.dictionary.model.Word;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class DictionaryParserImpl implements DictionaryParser {

    private final WordParser wordParser;
    private final DictionaryMongoRepository dictionaryMongoRepository;

    @Override
    public List<Dictionary> parse(String content, String firstLanguage, String secondLanguage) {
        List<Dictionary> dictionaries = new ArrayList<>();
        List<WordsParser> dictionaryDefinition = dictionaryDefinition(content);
        for (WordsParser wordDefinitions : dictionaryDefinition) {
            List<Word> words = wordDefinitions.parseWords(wordParser);
            List<UkrainianWord> urkWords = (List) words;
            Dictionary multiLanguageWord = new Dictionary(urkWords, ImmutableList.of());
            dictionaries.add(multiLanguageWord);
            String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);


            dictionaryMongoRepository.save(multiLanguageWord, collection);
        }
        return dictionaries;
    }

    private List<WordsParser> dictionaryDefinition(String content) {
        List<WordsParser> result = new ArrayList<>();
        List<String> wordDefinitions = new ArrayList<>();
        StringBuilder wordDefBuilder = new StringBuilder();

        String[] lines = content.trim().split("\n");
        for (String line : lines) {
            String pureLine = line.trim();
            if (Pattern.compile("⁕([⁕ ]+)⁕").matcher(pureLine).matches()) {
                if (!wordDefinitions.isEmpty()) {
                    result.add(new WordsParser(wordDefinitions));
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
        result.add(new WordsParser(wordDefinitions));
        return result;
    }
}
