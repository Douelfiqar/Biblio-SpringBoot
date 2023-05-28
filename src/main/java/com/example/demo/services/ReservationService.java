package com.example.demo.services;

import com.example.demo.entities.Document;
import com.example.demo.entities.Pret;
import com.example.demo.entities.Reservation;
import com.example.demo.repositories.ReservationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
@AllArgsConstructor
public class ReservationService {

    private ReservationRepo reservationRepo;
    private ClientService clientService;
    private DocService docService;
    public void addReservation(Long idDocument, String cinClient) {

        Reservation reservation = new Reservation();

        reservation.setClient(clientService.getClientByCIN(cinClient));
        reservation.setDate(new Date());
        reservation.setDocument(docService.getDocsById(idDocument));

        reservationRepo.save(reservation);


    }

    public Collection<Reservation> listReservation(Long idBook){
        return reservationRepo.listReservation(idBook);
    }
}
