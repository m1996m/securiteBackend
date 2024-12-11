package com.securite.Securite.dto.carteElectorale;

import com.securite.Securite.model.CarteElectorale;
import com.securite.Securite.model.Electeur;
import com.securite.Securite.model.Organisme;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarteElectoraleResponseDto {
    private long idCarteElectorale;
    private Date emissionDate;
    private Date expirationDate;
    private long carteNumber;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private long idElecteur;
    private String codeUnique;
}
