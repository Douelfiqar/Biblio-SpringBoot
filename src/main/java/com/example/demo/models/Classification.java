package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classification {
    private ArrayList<String> sujets ;
    private ArrayList<String> auteurs ;
    private ArrayList<String> titres ;
    private ArrayList<String> categories ;
}
