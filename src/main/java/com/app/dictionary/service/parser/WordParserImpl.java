package com.app.dictionary.service.parser;

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
    public Word parse(String wordStr) {
        Word word = new Word();

        if (wordStr.startsWith("! ")) {
            wordStr = StringUtils.replace(wordStr, "! ", "");
            //TODO:2021-05-08:yen: falseParallel
//            word.setImportant(true);
        }
        String[] wordToDef = wordStr.split(",", 2);
        word.setWord(wordToDef[0]);
        String def = wordToDef[1];
        String[] typeToDefinition = def.split(":", 2);
        //TODO:2021-04-10:yen: checkout prefixes
        word.setMorphologyEndings(typeToDefinition[0]);
        List<WordDefinition> wordDefinitions = wordDefinitionsParser.parse(typeToDefinition[1]);
        word.setDefinitions(wordDefinitions);
        return word;
    }
}
