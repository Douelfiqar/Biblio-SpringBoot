package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String linkImage;
    private String shortDesc;


    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Pret> pretCollection;
    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Location> locationCollection;
    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Reservation> reservationCollection;


    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bibliotheque bibliotheque;


}
