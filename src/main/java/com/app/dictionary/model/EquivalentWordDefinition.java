package com.app.dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EquivalentWordDefinition {

    private String precedingComment;
    private String definition;
}
