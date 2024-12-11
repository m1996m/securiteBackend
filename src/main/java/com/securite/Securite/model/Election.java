package com.securite.Securite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.securite.Securite.enumeration.TypeElection;
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
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idElection;
    @Column(nullable = false)
    private Date dateStart;
    @Column(nullable = false)
    private Date dateEnd;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private TypeElection typeElection = TypeElection.PRESIDENTIEL;
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
    @JoinColumn(name = "idOrganisme")
    private Organisme organisme;

    @JsonManagedReference
    @OneToMany(mappedBy = "election")
    private Set<Vote> votes;

    @JsonManagedReference
    @OneToMany(mappedBy = "election")
    private Set<Candidat> candidats;
}
