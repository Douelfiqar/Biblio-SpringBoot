package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Client client;
    @ManyToOne
    private Document document;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateDebutLocation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateFinLocation;
    @Column(name = "rendu", columnDefinition = "boolean default false")
    private boolean rendu;
}

