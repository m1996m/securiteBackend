package com.securite.Securite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.securite.Securite.enumeration.Statut;
import com.securite.Securite.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeclarationNaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDeclarationBirth;
    @Column(nullable = false)
    private LocalDateTime declartionDate;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false)
    private Statut statut = Statut.ATTENTE;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String slugOrganisme;
    @Column(nullable = false, updatable = false)
    private String codeUnique;
    private String rangNaissance;
    private String typeNaissance;
    private String lienDeclarant;

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
    @JoinColumn(name = "idOrganisme")
    private Organisme organisme;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idPere")
    private Person pere;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idEnfant")
    private Person enfant;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idMere")
    private Person mere;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idDeclarant")
    private Person declarant;

    @JsonManagedReference
    @OneToMany(mappedBy = "declarationNaissance")
    private Set<ExtraitNaissance> extraitNaissances;
}
