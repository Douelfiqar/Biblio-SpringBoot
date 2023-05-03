package com.example.demo.repositories;

import com.example.demo.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {
}