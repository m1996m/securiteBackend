package com.securite.Securite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.securite.Securite.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentDemande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDocumentDemande;
    @Column(nullable = false)
    private String type;
    private String slug;
    @Column(nullable = true)
    private String image;
    @Column(nullable = false)
    private String slugOrganisme;

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
    @JoinColumn(name = "idDemande")
    private Demande demande;
}
