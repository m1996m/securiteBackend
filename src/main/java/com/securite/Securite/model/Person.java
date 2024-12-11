package com.securite.Securite.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.securite.Securite.enumeration.Genre;
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
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPerson;
    @Column(nullable = false)
    private String firtName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String region;
    private String secteur;
    @Column(nullable = false)
    private String placeOfBirth;
    private String tel;
    @Column(unique = true, length = 7, updatable = false)
    private String slug;
    private String image;
    private Date dateOfBirth;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String nationality;
    @Column(nullable = false)
    private Genre genre = Genre.HOMME;
    private String profession;
    @Column(unique = true)
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

    @JsonManagedReference
    @OneToMany(mappedBy = "enfant")
    private Set<DeclarationNaissance> declarationNaissances;

    @JsonManagedReference
    @OneToMany(mappedBy = "person")
    private Set<ExtraitNaissance> extraitNaissances;

    @JsonManagedReference
    @OneToMany(mappedBy = "person")
    private Set<CarteIdentite> carteIdentites;

    @JsonManagedReference
    @OneToMany(mappedBy = "person")
    private Set<Passeport> passeports;

    @JsonManagedReference
    @OneToMany(mappedBy = "person")
    private Set<Electeur> electeurs;

    @JsonManagedReference
    @OneToMany(mappedBy = "person")
    private Set<Demande> demandes;
}
