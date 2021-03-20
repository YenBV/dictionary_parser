package com.app.dictionary.service;

import com.app.dictionary.model.Word;

public interface WordParser {

    Word parse(String wordDefinition);
}
