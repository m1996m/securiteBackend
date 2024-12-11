package com.securite.Securite.repository.nature;

import com.securite.Securite.model.Sequence_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Sequence_tableRepository extends JpaRepository<Sequence_table, Long> {
    Sequence_table findByName(String name);
}
