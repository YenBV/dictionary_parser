package com.app.dictionary.model;

import lombok.Data;

@Data
public class GermanWord extends Word {
    private String language = "german";
    private Dictionary dictionary;
}
