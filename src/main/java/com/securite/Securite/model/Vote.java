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
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVote;
    @Column(nullable = false)
    private Date dateVote;
    @Column(nullable = false)
    private String slug;
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
    @JoinColumn(name = "idElection")
    private Election election;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idElecteur")
    private Electeur electeur;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idCandidat")
    private Candidat candidat;

}
