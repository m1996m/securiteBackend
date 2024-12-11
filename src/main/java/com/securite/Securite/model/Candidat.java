package com.securite.Securite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCandidat;

    @Column(nullable = false)
    private String firtName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String partiPolitique;

    @Column(unique = true, length = 7, updatable = false)
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
    @JoinColumn(name = "idElection")
    private Election election;

    @JsonManagedReference
    @OneToMany(mappedBy = "candidat")
    private Set<Vote> votes;
}
