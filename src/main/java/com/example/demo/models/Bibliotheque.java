package com.example.demo.models;

import com.example.demo.entities.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bibliotheque {
        private ArrayList<Document> documents ;
        private Classification classification ;
}
