package com.app.dictionary.service;

import com.app.dictionary.model.WordDefinition;

public interface WordDefinitionParser {

    WordDefinition parse(String wordDefinition);
}
