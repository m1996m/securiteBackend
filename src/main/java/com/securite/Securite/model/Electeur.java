package com.securite.Securite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.securite.Securite.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Electeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idElecteur;
    @Column(nullable = false)
    private Date dateInscription;
    @Column(nullable = false)
    private boolean statutIscription = true;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String slugOrganisme;
    @Column(nullable = false, updatable = false)
    private String codeUnique;

    @PrePersist
    public void generateSlugBeforePersist() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugGenerator.generateSlug();
        }
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;

    @JsonManagedReference
    @OneToMany(mappedBy = "electeur")
    private Set<Vote> votes;

    @JsonManagedReference
    @OneToMany(mappedBy = "electeur")
    private Set<CarteElectorale> carteElectorales;
}
