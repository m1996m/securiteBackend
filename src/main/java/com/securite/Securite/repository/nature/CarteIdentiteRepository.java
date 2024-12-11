package com.securite.Securite.repository.nature;

import com.securite.Securite.model.CarteIdentite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteIdentiteRepository extends JpaRepository<CarteIdentite, Long> {
}
