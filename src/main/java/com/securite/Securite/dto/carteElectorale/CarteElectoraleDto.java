package com.securite.Securite.dto.carteElectorale;

import com.securite.Securite.model.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarteElectoraleDto {
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


    public CarteElectorale create(CarteElectoraleDto carteElectoraleDto, Electeur electeur, Organisme organisme){
       CarteElectorale carteElectorale = new CarteElectorale();
        carteElectorale.setCarteNumber(carteElectoraleDto.getCarteNumber());
        carteElectorale.setEmissionDate(carteElectoraleDto.getEmissionDate());
        carteElectorale.setExpirationDate(carteElectoraleDto.getExpirationDate());
        carteElectorale.setElecteur(electeur);
        carteElectorale.setOrganisme(organisme);
        carteElectorale.setSlugOrganisme(carteElectoraleDto.slugOrganisme);
        carteElectorale.setCodeUnique(electeur.getPerson().getCodeUnique());

       return carteElectorale;
   }

    public CarteElectorale update(CarteElectoraleDto carteElectoraleDto, CarteElectorale carteElectorale){
        carteElectorale.setCarteNumber(carteElectoraleDto.getCarteNumber());
        carteElectorale.setEmissionDate(carteElectoraleDto.getEmissionDate());
        carteElectorale.setExpirationDate(carteElectoraleDto.getExpirationDate());

        return carteElectorale;
    }
}
