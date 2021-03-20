package com.app.dictionary.service;

import com.app.dictionary.model.Dictionary;

public interface DictionaryParser {

    Dictionary parse(String content);
}
