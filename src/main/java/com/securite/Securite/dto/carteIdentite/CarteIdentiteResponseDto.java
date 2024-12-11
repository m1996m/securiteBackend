package com.securite.Securite.dto.carteIdentite;

import com.securite.Securite.model.CarteIdentite;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.Person;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarteIdentiteResponseDto {
    private long idCardIdentity;
    private Date delivranceDate;
    private Date expirationDate;
    private long cardNumber;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private long idPerson;
    private String codeUnique;
}
