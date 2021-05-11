package com.app.dictionary.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WordUtils {
    public static List<String> ngram(int count, String string) {
        int start  = 0;
        int end = Math.min(count, string.length());
        List<String> result = new ArrayList<>();

        while (end <= string.length()) {
            result.add(string.substring(start, end));
            start++;
            end++;
        }

        return result;
    }

    public static String getNgramsForWord(String word) {
        if (word.length() == 4) {
            return toNgramString(ngram(2, word));
        } else if (word.length() <= 10) {
            return toNgramString(ngram(3, word));
        } else if (word.length() <= 15) {
            return toNgramString(ngram(4, word));
        } else {
            return toNgramString(ngram(5, word));
        }
    }

    private static String toNgramString(List<String> list) {
        return String.join(" ", list);
    }
}
