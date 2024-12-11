package com.securite.Securite.repository.nature;

import com.securite.Securite.model.Passeport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasseportRepository extends JpaRepository<Passeport, Long> {
}
