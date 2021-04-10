package com.app.dictionary.service.parser;

import com.app.dictionary.model.UkrainianWord;
import com.app.dictionary.model.Word;
import com.app.dictionary.model.WordDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordParserImpl implements WordParser {

    private final WordDefinitionsParser wordDefinitionsParser;

    public WordParserImpl(
            WordDefinitionsParser wordDefinitionsParser) {
        this.wordDefinitionsParser = wordDefinitionsParser;
    }

    @Override
    public Word parse(String wordDefinition) {
        UkrainianWord word = new UkrainianWord();

        if (wordDefinition.startsWith("! ")) {
            wordDefinition = StringUtils.replace(wordDefinition, "! ", "");
            word.setImportant(true);
        }
        String[] wordToDef = wordDefinition.split(",", 2);
        word.setWord(wordToDef[0]);
        String def = wordToDef[1];
        String[] typeToDefinition = def.split(":", 2);
        word.setType(typeToDefinition[0]);
        List<WordDefinition> wordDefinitions = wordDefinitionsParser.parse(typeToDefinition[1]);
//        for (WordDefinition definition : wordDefinitions) {
//            definition.setWord(word);
//        }
        word.setDefinitions(wordDefinitions);
        return word;
    }
}
