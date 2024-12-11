package com.securite.Securite.repository.nature;

import com.securite.Securite.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
}
