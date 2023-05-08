package com.example.demo.entities;

import com.example.demo.entities.Document;
import com.example.demo.models.Classification;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bibliotheque {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String Address;

        @OneToMany(mappedBy = "bibliotheque", fetch = FetchType.EAGER)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private Collection<Document> documentCollection ;

}
