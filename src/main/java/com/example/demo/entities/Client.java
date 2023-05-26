package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Column(unique = true)
    private String CIN;
    private String nom;
    private String prenom;
    private String email;
    private String address;
    private String numTelephone;
    private int nmbreAllocationEnCours;
    @Column(name = "isAdherent", columnDefinition = "boolean default false")
    private boolean isAdherent;
    public boolean getIsAdherent(){
        return isAdherent;
    }

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Location> locationCollection;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Reservation> reservationCollection;
}
