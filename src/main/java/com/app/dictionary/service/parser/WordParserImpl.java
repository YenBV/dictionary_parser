package com.app.dictionary.service.parser;

import com.app.dictionary.dao.UkrainianWordRepository;
import com.app.dictionary.model.UkrainianWord;
import com.app.dictionary.model.Word;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class WordParserImpl implements WordParser {

    private final WordDefinitionsParser wordDefinitionsParser;
    private final UkrainianWordRepository repository;

    public WordParserImpl(
            WordDefinitionsParser wordDefinitionsParser,
            UkrainianWordRepository repository) {
        this.wordDefinitionsParser = wordDefinitionsParser;
        this.repository = repository;
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
        word.setDefinitions(wordDefinitionsParser.parse(typeToDefinition[1]));
        repository.save(word);
        return word;
    }
}
