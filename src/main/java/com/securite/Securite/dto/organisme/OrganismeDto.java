package com.securite.Securite.dto.organisme;

import com.securite.Securite.model.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrganismeDto {
    private long idOrganisme;
    private String email;
    private String name;
    private String address;
    private String type;
    private String tel;
    private String slug;
    private String image;
    private LocalDateTime createdAt;

    public Organisme create(OrganismeDto dto){
        Organisme organisme = new Organisme();
        organisme.setName(dto.getName());
        organisme.setAddress(dto.getAddress());
        organisme.setEmail(dto.getEmail());
        organisme.setTel(dto.getTel());
        organisme.setType(dto.getType());

        return organisme;
   }

    public Organisme update(OrganismeDto dto, Organisme organisme){
        organisme.setName(dto.getName());
        organisme.setAddress(dto.getAddress());
        organisme.setEmail(dto.getEmail());
        organisme.setTel(dto.getType());
        organisme.setType(dto.getTel());
        organisme.setImage(dto.getImage());

        return organisme;
    }
}
