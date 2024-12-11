package com.securite.Securite.dto.extraitNaissance;

import com.securite.Securite.model.ExtraitNaissance;
import com.securite.Securite.model.Person;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExtraitNaissanceResponseDto {
    private long idExtraitNaissance;
    private Date dateEmission;
    private long extraitNumber;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idPerson;
    private String codeUnique;
    private long idDeclarationNaissance;
    private String lienDeclarant;
}
