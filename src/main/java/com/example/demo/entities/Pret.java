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
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateDebutPretation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateFinPretation;
    @ManyToOne
    private Adherent adherent;
    @ManyToOne
    private Document document;

}
