package com.app.dictionary.service;

import com.app.dictionary.model.Dictionary;
import com.app.dictionary.model.Word;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class DictionaryParserImpl implements DictionaryParser {
    @Override
    public void parse(String content) {
        Dictionary dictionary = new Dictionary();
        List<String> wordDefinitions = wordDefinitions(content);

        for (String wordDefinition : wordDefinitions) {

            Word.Builder word = Word.newBuilder();

            if (wordDefinition.startsWith("! ")) {
                wordDefinition = StringUtils.replace(wordDefinition, "! ", "");
                word.setImportant(true);
            }
            String[] wordToDef = wordDefinition.split(",", 2);
            word.setWord(wordToDef[0]);
            String all = wordToDef[1];
            String[] typeToDefinition = all.split(":", 2);
            word.setType(typeToDefinition[0]);
            word.setDefinition(typeToDefinition[1]);
            dictionary.add(word.build());
        }


        for (Word word : dictionary.getWords()) {
            System.out.println(word);
        }
    }

    private List<String> wordDefinitions(String content) {
        List<String> result = new ArrayList<>();

        String[] lines = content.trim().split("\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            String pureLine = line.trim();
            if (Pattern.compile("⁕([⁕ ]+)⁕").matcher(pureLine).matches()) {
                continue;
            }
            if (Pattern.compile("^(! )?(\\S+), (-?)([а-я]|[a-z]){1,4}(, [а-я]|[a-z])?(.*):$").matcher(pureLine).matches()) {
                String tr = sb.toString();
                if (StringUtils.isNotBlank(tr)) {
                    result.add(tr);
                }
                sb = new StringBuilder();
            }
            sb.append(pureLine);
        }
        result.add(sb.toString());
        return result;
    }
}
