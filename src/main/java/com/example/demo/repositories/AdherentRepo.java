package com.example.demo.repositories;

import com.example.demo.entities.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepo extends JpaRepository<Adherent, Long> {
    public Adherent findAdherentByCodeAdherent(String codeAdherent);


}
