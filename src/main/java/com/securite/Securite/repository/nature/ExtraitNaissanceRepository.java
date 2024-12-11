package com.securite.Securite.repository.nature;

import com.securite.Securite.model.ExtraitNaissance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraitNaissanceRepository extends JpaRepository<ExtraitNaissance, Long> {
}
