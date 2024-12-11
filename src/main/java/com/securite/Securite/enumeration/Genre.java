package com.securite.Securite.enumeration;

import lombok.Getter;

@Getter
public enum Genre {
    HOMME("Homme"),
    FEMME("Femme"),
    AUTRE("Non précisé");

    private final String displayName;

    Genre(String displayName){
        this.displayName = displayName;
    }

}
