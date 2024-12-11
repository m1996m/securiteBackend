package com.securite.Securite.repository.nature;

import com.securite.Securite.model.CarteElectorale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteElectoraleRepository extends JpaRepository<CarteElectorale, Long> {
}
