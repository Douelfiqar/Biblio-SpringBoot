package com.example.demo;

import com.example.demo.entities.Bibliotheque;
import com.example.demo.entities.Document;
import com.example.demo.repositories.BiblioRepo;
import com.example.demo.repositories.DocumentRepo;
import com.example.demo.services.BiblioService;
import com.example.demo.services.DocService;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.*;

@SpringBootApplication
public class Demo10Application {

    @Autowired
    private BiblioService biblioService;
    @Autowired
    private DocService docService;

    public static void main(String[] args) {
        SpringApplication.run(Demo10Application.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    CommandLineRunner commandLineRunner(){
        return args -> {

            Bibliotheque bibliotheque = biblioService.getBiblio();
            Faker faker = new Faker();
            Random rand = new Random();

            for(int i = 0 ;i<100; i++){
                Document doc1 = new Document();
                doc1.setAuteur(faker.book().author());
                doc1.setCategorie(faker.book().genre());
                doc1.setSujet(faker.lorem().paragraph());
                doc1.setTitre(faker.book().title());
                doc1.setDateEdition(new Date());
                doc1.setPrixLocation(rand.nextInt(901) + 100);
                doc1.setNombreExemplaire(rand.nextInt(30) + 1);
                doc1.setBibliotheque(bibliotheque);
                doc1.setLinkImage(faker.internet().url());
                doc1.setShortDesc(faker.lorem().paragraph());
                docService.saveDoc(doc1);
            }









        };
    }
}
