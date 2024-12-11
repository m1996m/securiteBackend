package com.securite.Securite.repository.nature;

import com.securite.Securite.model.Electeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElecteurRepository extends JpaRepository<Electeur, Long> {
}
