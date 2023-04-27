package com.example.demo.repositories;


import com.example.demo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<Document, Long> {
}
