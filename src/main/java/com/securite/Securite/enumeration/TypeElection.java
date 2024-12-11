package com.securite.Securite.enumeration;

import lombok.Getter;

@Getter
public enum TypeElection {
    PRESIDENTIEL("Presidentielle"),
    COMMUNALE("Communale"),
    LEGISLATIVE("Legislative");


    private final String displayName;

    TypeElection(String displayName){
        this.displayName = displayName;
    }
}
