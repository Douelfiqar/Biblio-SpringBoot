package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @ManyToOne
    private Adherent adherent;
    @ManyToOne
    private Bibliotheque bibliotheque;


}
