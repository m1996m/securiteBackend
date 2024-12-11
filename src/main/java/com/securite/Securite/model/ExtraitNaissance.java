package com.securite.Securite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.securite.Securite.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExtraitNaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idExtraitNaissance;
    @Column(nullable = false)
    private Date dateEmission;
    @Column(nullable = false, unique = true)
    private long extraitNumber;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String slugOrganisme;
    @Column(nullable = false, updatable = false)
    private String codeUnique;
    @Column(nullable = false, updatable = false)
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
    @JoinColumn(name = "idPerson")
    private Person person;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idDeclarationBirth")
    private DeclarationNaissance declarationNaissance;
}
