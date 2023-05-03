package com.example.demo.entities;

import com.example.demo.models.Bibliotheque;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Document  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String sujet;
    private String titre;
    private String auteur;
    private String categorie;
    private int nombreExemplaire;
    private Date dateEdition;
    private float prixLocation;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    private Collection<Pret> pretCollection;
    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    private Collection<Location> locationCollection;
    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    private Collection<Reservation> reservationCollection;

    //@OneToMany
    //private Bibliotheque bibliotheque;


}
