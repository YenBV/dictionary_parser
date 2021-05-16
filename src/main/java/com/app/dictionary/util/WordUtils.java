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
        String processedWord = word.replaceAll("(?U)[^\\p{Alpha}]", "");
        if (processedWord.length() == 4) {
            return toNgramString(ngram(2, processedWord));
        } else if (processedWord.length() <= 10) {
            return toNgramString(ngram(3, processedWord));
        } else if (processedWord.length() <= 15) {
            return toNgramString(ngram(4, processedWord));
        } else {
            return toNgramString(ngram(5, processedWord));
        }
    }

    private static String toNgramString(List<String> list) {
        return String.join(" ", list);
    }
}
