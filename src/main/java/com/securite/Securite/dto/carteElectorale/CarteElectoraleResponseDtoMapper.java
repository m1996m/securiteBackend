package com.securite.Securite.dto.carteElectorale;

import com.securite.Securite.generic.GenericResponseDtoMapper;
import com.securite.Securite.model.CarteElectorale;
import org.springframework.stereotype.Component;

@Component
public class CarteElectoraleResponseDtoMapper implements
        GenericResponseDtoMapper<CarteElectoraleResponseDto, CarteElectorale> {
    @Override
    public CarteElectoraleResponseDto toResponseDto(CarteElectorale carteElectorale) {
        return CarteElectoraleResponseDto.builder()
                .idCarteElectorale(carteElectorale.getIdCarteElectorale())
                .carteNumber(carteElectorale.getCarteNumber())
                .slug(carteElectorale.getSlug())
                .createdAt(carteElectorale.getCreatedAt())
                .emissionDate(carteElectorale.getEmissionDate())
                .expirationDate(carteElectorale.getExpirationDate())
                .idElecteur(carteElectorale.getElecteur().getIdElecteur())
                .idOrganisme(carteElectorale.getOrganisme().getIdOrganisme())
                .slugOrganisme(carteElectorale.getSlugOrganisme())
                .slug(carteElectorale.getSlug())
                .build();
    }
}
