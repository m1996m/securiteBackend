package com.securite.Securite.dto.declarationNaissance;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.DeclarationNaissance;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.Person;
import com.securite.Securite.service.DeclarationNaissanceService;
import com.securite.Securite.service.OrganismeService;
import com.securite.Securite.service.PersonService;
import org.springframework.stereotype.Component;

@Component
public class DeclarationNaissanceDtoMapper implements
        GenericDtoMapper<DeclarationNaissanceDto, DeclarationNaissance> {
    private final DeclarationNaissanceService service;
    private final OrganismeService organismeService;
    private final PersonService personService;

    public DeclarationNaissanceDtoMapper(
            DeclarationNaissanceService service,
            OrganismeService organismeService,
            PersonService personService
    ) {
        this.service = service;
        this.organismeService = organismeService;
        this.personService = personService;
    }

    @Override
    public DeclarationNaissanceDto toDto(DeclarationNaissance declarationNaissance) {
        return DeclarationNaissanceDto.builder()
                .place(declarationNaissance.getPlace())
                .declartionDate(declarationNaissance.getDeclartionDate())
                .idEnfant(declarationNaissance.getEnfant().getIdPerson())
                .idOrganisme(declarationNaissance.getOrganisme().getIdOrganisme())
                .slug(declarationNaissance.getSlug())
                .idDeclarationBirth(declarationNaissance.getIdDeclarationBirth())
                .slugOrganisme(declarationNaissance.getSlugOrganisme())
                .build();
    }

    @Override
    public DeclarationNaissance toEntity(DeclarationNaissanceDto dto) {
        Person pere = personService.findUniqueById(dto.getIdPere());
        Person mere = personService.findUniqueById(dto.getIdMere());
        Person enfant = personService.findUniqueById(dto.getIdEnfant());
        Person declarant = personService.findUniqueById(dto.getIdDeclarant());
        Organisme organisme = organismeService.findUniqueById(dto.getIdOrganisme());

        if (dto.getSlug() != null) {
            DeclarationNaissance declaration = service
                    .findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, organisme, pere, mere, enfant, declarant, declaration);
        }else {
            dto.setSlugOrganisme(organisme.getSlug());
            dto.setCodeUnique(enfant.getCodeUnique());

            return dto.create(dto, organisme, pere, mere, enfant, declarant);
        }
    }
}
