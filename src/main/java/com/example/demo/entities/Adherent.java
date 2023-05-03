package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class Adherent extends Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeAdherent;

    @OneToMany(mappedBy = "adherent", fetch = FetchType.LAZY)
    private Collection<Pret> pretCollection;
}
