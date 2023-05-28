package com.example.demo.repositories;

import com.example.demo.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ReservationRepo extends JpaRepository<Reservation, Long>{

    @Query("SELECT r FROM Reservation r WHERE r.document.id = :idBook")
    public Collection<Reservation> listReservation(Long idBook);
}
