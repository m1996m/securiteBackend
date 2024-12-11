package com.securite.Securite.repository.nature;

import com.securite.Securite.model.DocumentDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDemandeRepository extends JpaRepository<DocumentDemande, Long> {
}
