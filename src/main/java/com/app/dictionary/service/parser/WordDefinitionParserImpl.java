package com.app.dictionary.service.parser;

import com.app.dictionary.model.WordDefinition;
import org.springframework.stereotype.Service;

@Service
public class WordDefinitionParserImpl implements WordDefinitionParser {

    @Override
    public WordDefinition parse(String wordDefinition) {
        String[] definitionToExample = wordDefinition.split("[0,9]?:");
        if (definitionToExample.length == 1) {
            return new WordDefinition(definitionToExample[0]);
        } else if (definitionToExample.length > 1) {
            return new WordDefinition(definitionToExample[0], definitionToExample[1]);
        }
        throw new RuntimeException("Can't parse word definition for the supplied " + wordDefinition);
    }
}
