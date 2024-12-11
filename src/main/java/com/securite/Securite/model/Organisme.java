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
public class Organisme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrganisme;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String tel;
    @Column(unique = true, length = 7, updatable = false)
    private String slug;
    @Column(nullable = true)
    private String image;

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
    @OneToMany(mappedBy = "organisme")
    private Set<User> users;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<DeclarationNaissance> declarationNaissances;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<CarteIdentite> carteIdentites;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<Passeport> passeports;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<Election> elections;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<CarteElectorale> carteElectorales;
}
