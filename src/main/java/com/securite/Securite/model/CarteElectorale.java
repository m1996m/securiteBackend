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
public class CarteElectorale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCarteElectorale;
    @Column(nullable = false)
    private Date emissionDate;
    @Column(nullable = false)
    private Date expirationDate;
    @Column(nullable = false, unique = true)
    private long carteNumber;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false, updatable = false)
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
    @JoinColumn(name = "idOrganisme")
    private Organisme organisme;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idElecteur")
    private Electeur electeur;
}
