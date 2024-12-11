package com.securite.Securite.dto.person;

import com.securite.Securite.dto.carteIdentite.CarteIdentiteResponseDto;
import com.securite.Securite.dto.declarationNaissance.DeclarationNaissanceResponseDto;
import com.securite.Securite.dto.documentDemande.DocumentDemandeDto;
import com.securite.Securite.dto.electeur.ElecteurResponseDto;
import com.securite.Securite.dto.extraitNaissance.ExtraitNaissanceDto;
import com.securite.Securite.dto.passeport.PasseportResponseDto;
import com.securite.Securite.model.Demande;
import com.securite.Securite.model.DocumentDemande;
import com.securite.Securite.model.Person;
import lombok.*;
import org.w3c.dom.ls.LSInput;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonResponseDto {
    private long idPerson;
    private String firtName;
    private String lastName;
    private String address;
    private String placeOfBirth;
    private String tel;
    private String slug;
    private String image;
    private Date dateOfBirth;
    private String email;
    private String nationality;
    private String codeUnique;
    private String region;
    private String secteur;
    private List<DeclarationNaissanceResponseDto> declarationNaissances;
    private List<CarteIdentiteResponseDto> carteIdentites;
    private List<PasseportResponseDto> passeports;
    private List<ElecteurResponseDto> electeurs;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private List<Demande> demandes;
    private List<ExtraitNaissanceDto> extraitNaissances;
}
