package com.securite.Securite.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sequence_table {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private long next_value;
}
