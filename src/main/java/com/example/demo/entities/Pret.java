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
public class Pret {
    @Idf
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "code_adherent")
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    private Date datePret;
}
