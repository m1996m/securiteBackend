package com.securite.Securite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.securite.Securite.enumeration.Genre;
import com.securite.Securite.enumeration.Role;
import com.securite.Securite.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = true)
    private String firstName;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String tel;
    @Column(nullable = true)
    private String lastName;
    @Getter
    private String password;
    @Column(unique = true, length = 30)
    private String slug;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'USER'")
    private Role role = Role.USER;
    @Column(nullable = true)
    private String avatar;
    @Column(nullable = true)
    private Genre gender = Genre.AUTRE;
    @Column(nullable = false)
    private boolean isActif = true;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
