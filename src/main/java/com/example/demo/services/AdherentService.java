package com.example.demo.services;

import com.example.demo.entities.Adherent;
import com.example.demo.entities.Client;
import com.example.demo.repositories.AdherentRepo;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdherentService {
    private AdherentRepo adherentRepo;
    public void addAdherent(Adherent adherent){
        Faker faker = new Faker();
        adherent.setCIN(faker.idNumber().valid());
        adherent.setCodeAdherent(faker.idNumber().valid());
        adherentRepo.save(adherent);
    }

    public Optional<Adherent> getAdherentById(Long id) {
        return adherentRepo.findById(id);
    }


    public Adherent findAdherentByCodeAdherent(String codeAdherent){
        return adherentRepo.findAdherentByCodeAdherent(codeAdherent);
    }
    public Collection<Adherent> getAllAdherent(){
        return adherentRepo.findAll();
    }

}
