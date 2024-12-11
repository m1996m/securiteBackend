package com.securite.Securite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.securite.Securite.enumeration.Statut;
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
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDemande;
    @Column(nullable = false)
    private Date dateDemande;
    @Column(nullable = false)
    private Statut statut = Statut.ATTENTE;
    @Column(nullable = false)
    private Statut typeDocument;
    @Column(nullable = false, unique = true)
    private long numeroDemande;
    @Column(nullable = false)
    private Date dateValidation = new Date();
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
    @OneToMany(mappedBy = "demande")
    private Set<DocumentDemande> documentDemandes;
}
