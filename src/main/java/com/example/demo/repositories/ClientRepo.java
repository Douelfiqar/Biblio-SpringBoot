package com.example.demo.repositories;

import com.example.demo.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
