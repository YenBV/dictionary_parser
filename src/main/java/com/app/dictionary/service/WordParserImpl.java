package com.app.dictionary.service;

import com.app.dictionary.model.Word;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class WordParserImpl implements WordParser {

    @Override
    public Word parse(String wordDefinition) {
        Word.Builder word = Word.newBuilder();

        if (wordDefinition.startsWith("! ")) {
            wordDefinition = StringUtils.replace(wordDefinition, "! ", "");
            word.setImportant(true);
        }
        String[] wordToDef = wordDefinition.split(",", 2);
        word.setWord(wordToDef[0]);
        String def = wordToDef[1];
        String[] typeToDefinition = def.split(":", 2);
        word.setType(typeToDefinition[0]);
        word.setDefinition(typeToDefinition[1]);
        return word.build();
    }
}
