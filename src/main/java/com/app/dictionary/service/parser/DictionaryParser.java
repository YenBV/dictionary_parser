package com.app.dictionary.service.parser;

import com.app.dictionary.model.Dictionary;

import java.util.List;

public interface DictionaryParser {

    List<Dictionary> parse(String content);
}
