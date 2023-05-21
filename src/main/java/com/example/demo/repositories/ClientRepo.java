package com.example.demo.repositories;

import com.example.demo.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    public Client getClientByCIN(String cin);
    @Query("SELECT c FROM Client c WHERE c.CIN LIKE CONCAT('%', :term, '%')")
    public Collection<Client> getClientLikeCin(String term);
}
