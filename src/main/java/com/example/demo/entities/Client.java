package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String CIN;
    private String nom;
    private String prenom;
    private String email;
    private String address;
    private String numTelephone;
    private int nmbreAllocationEnCours;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Location> locationCollection;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Reservation> reservationCollection;
}
