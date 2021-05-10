package com.app.dictionary.service.parser;

import com.app.dictionary.dao.WordArticleMongoRepository;
import com.app.dictionary.model.Word;
import com.app.dictionary.model.WordArticle;
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
    private final WordArticleMongoRepository wordArticleMongoRepository;

    @Override
    public List<WordArticle> parse(String content, String firstLanguage, String secondLanguage) {
        List<WordArticle> dictionaries = new ArrayList<>();
        List<WordsParser> dictionaryDefinition = dictionaryDefinition(content);
        for (WordsParser wordDefinitions : dictionaryDefinition) {
            List<Word> words = wordDefinitions.parseWords(wordParser);
            //TODO:2021-05-08:yen: check it
            WordArticle multiLanguageWord = new WordArticle(words.get(0), words.subList(1, words.size()));
            dictionaries.add(multiLanguageWord);
            String collection = String.format("%s_%s_articles", firstLanguage, secondLanguage);


            wordArticleMongoRepository.save(multiLanguageWord, collection);
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
            wordDefBuilder.append(pureLine).append(" ");
        }
        wordDefinitions.add(wordDefBuilder.toString());
        result.add(new WordsParser(wordDefinitions));
        return result;
    }
}
