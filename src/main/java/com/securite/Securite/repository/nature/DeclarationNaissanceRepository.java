package com.securite.Securite.repository.nature;

import com.securite.Securite.model.DeclarationNaissance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationNaissanceRepository extends JpaRepository<DeclarationNaissance, Long> {
}
