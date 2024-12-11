package com.securite.Securite.dto.passeport;

import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.Passeport;
import com.securite.Securite.model.Person;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasseportResponseDto {
    private long idPasseport;
    private Date delivranceDate;
    private Date expirationDate;
    private String passportNumber;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private long idPerson;
    private String codeUnique;
}
