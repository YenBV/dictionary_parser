package com.app.dictionary.service.parser;

import com.app.dictionary.model.EquivalentWordDefinition;
import com.app.dictionary.model.WordDefinition;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordDefinitionParserImpl implements WordDefinitionParser {

    @Override
    public WordDefinition parse(String wordDefinition) {
        String[] definitionToExample = wordDefinition.split("[0,9]?:");
        WordDefinition def = new WordDefinition();
        if (definitionToExample.length >= 1) {
            def.setDefinition(definitionToExample[0]);
        }
        if (definitionToExample.length == 2) {
            String example = definitionToExample[1];
            String[] exampleToAdditionalDefs = example.split("-");
            if (exampleToAdditionalDefs.length >= 1) {
                def.setExample(exampleToAdditionalDefs[0]);
            }
            if (exampleToAdditionalDefs.length == 2) {
                String additionalDefs = exampleToAdditionalDefs[1];
                List<EquivalentWordDefinition> equivalentWordDefinitions = Arrays.stream(additionalDefs.split(",")).map(a -> {
                    EquivalentWordDefinition equivalentWordDefinition = new EquivalentWordDefinition();
                    equivalentWordDefinition.setDefinition(a);
                    return equivalentWordDefinition;
                }).collect(Collectors.toList());
                def.setEquivalentDefinitions(equivalentWordDefinitions);
            }
        }
        return def;
    }
}
