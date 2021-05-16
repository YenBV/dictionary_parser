package com.app.dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class EquivalentWordDefinition {
    @Size(max = 50)
    private String precedingComment;

    @Size(max = 50)
    private String definition;
}
