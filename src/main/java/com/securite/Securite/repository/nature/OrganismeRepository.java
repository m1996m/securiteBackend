package com.securite.Securite.repository.nature;

import com.securite.Securite.model.Organisme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismeRepository extends JpaRepository<Organisme, Long> {
}
