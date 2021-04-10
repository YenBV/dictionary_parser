package com.app.dictionary.model;

public enum Language {
    UKRAINIAN("ukrainian"),
    GERMANY("germany");

    private final String value;

    Language(String value) {
        this.value = value;
    }


    public static Language determineLanguage(String input) {
        for (Language language : values()) {
            if (language.value.equalsIgnoreCase(input)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Can't determine language from the supplied " + input);
    }
}
