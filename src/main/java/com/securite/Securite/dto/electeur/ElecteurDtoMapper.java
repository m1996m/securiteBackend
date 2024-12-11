package com.securite.Securite.dto.electeur;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.Electeur;
import com.securite.Securite.model.Person;
import com.securite.Securite.service.ElecteurService;
import com.securite.Securite.service.PersonService;
import org.springframework.stereotype.Component;

@Component
public class ElecteurDtoMapper implements GenericDtoMapper<ElecteurDto, Electeur> {
    private final PersonService personService;
    private final ElecteurService electeurService;

    public ElecteurDtoMapper(PersonService personService, ElecteurService electeurService) {
        this.personService = personService;
        this.electeurService = electeurService;
    }

    @Override
    public ElecteurDto toDto(Electeur electeur) {
        return ElecteurDto.builder()
                .idElecteur(electeur.getIdElecteur())
                .slugOrganisme(electeur.getSlugOrganisme())
                .slug(electeur.getSlug())
                .dateInscription(electeur.getDateInscription())
                .createdAt(electeur.getCreatedAt())
                .idPerson(electeur.getPerson().getIdPerson())
                .statutIscription(electeur.isStatutIscription())
                .build();
    }

    @Override
    public Electeur toEntity(ElecteurDto dto) {
        Person person = personService.findUniqueById(dto.getIdPerson());

        if (dto.getSlug() != null){
            Electeur electeur = electeurService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, person, electeur);
        }else {
            dto.setCodeUnique(person.getCodeUnique());
            return dto.create(dto,person);
        }
    }
}
