package com.example.demo;

import com.example.demo.entities.Location;
import com.example.demo.entities.Pret;
import com.example.demo.services.LocationService;
import com.example.demo.services.PreterService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

@Component
@AllArgsConstructor
public class ScheduledTask {
    PreterService preterService;
    LocationService locationService;

    @Scheduled(fixedRate = 86400000) // Run every 24 hours
    public void yourScheduledFunction() {
        // Implement your logic here
        Collection<Pret> pretCollection = preterService.findAllPreter();
        LocalDate currentDate = LocalDate.now();

        for (Pret pret : pretCollection) {
            Date dateFinPretation = pret.getDateFinPretation();
            LocalDate localDateFinPretation = dateFinPretation.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localDateFinPretation.isAfter(currentDate)) {
                double amende = pret.getAmende() + 5;
                pret.setAmende(amende);
                preterService.savePret(pret);
            }
        }
        Collection<Location> locationCollection = locationService.findAll();
        for(Location location:locationCollection){
            Date dateFinLocation = location.getDateFinLocation();
            LocalDate localDateFinLocation = dateFinLocation.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (localDateFinLocation.isAfter(currentDate)) {
                double amende = location.getAmende() + 10;
                location.setAmende(amende);
                locationService.saveLocation(location);
            }
        }
    }
}
