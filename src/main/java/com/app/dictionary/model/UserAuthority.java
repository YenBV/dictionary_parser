package com.app.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {
    CHANGE_PASSWORD, ALL;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return toString();
    }
}
