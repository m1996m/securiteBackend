package com.securite.Securite.dto.organisme;

import com.securite.Securite.dto.Election.ElectionResponseDto;
import com.securite.Securite.dto.carteElectorale.CarteElectoraleResponseDto;
import com.securite.Securite.dto.carteIdentite.CarteIdentiteResponseDto;
import com.securite.Securite.dto.declarationNaissance.DeclarationNaissanceResponseDto;
import com.securite.Securite.dto.passeport.PasseportResponseDto;
import com.securite.Securite.dto.user.UserResponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrganismeResponseDto {
    private long idOrganisme;
    private String email;
    private String name;
    private String address;
    private String type;
    private String tel;
    private String slug;
    private String image;
    private LocalDateTime createdAt;
    private long idUser;
    private List<DeclarationNaissanceResponseDto> declarationNaissances;
    private List<CarteIdentiteResponseDto> carteIdentites;
    private List<PasseportResponseDto> passeports;
    private List<ElectionResponseDto> elections;
    private List<CarteElectoraleResponseDto> carteElectorales;
    private List<UserResponseDto> users;
}
