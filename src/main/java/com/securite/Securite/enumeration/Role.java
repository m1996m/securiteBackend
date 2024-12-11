package com.securite.Securite.enumeration;

import lombok.Getter;

@Getter
public enum Role {
    USER("USER_ROLE"),
    ADMIN("ADMIN_ROLE");

    private final String displayName;

    Role(String displayName){
        this.displayName = displayName;
    }
}
