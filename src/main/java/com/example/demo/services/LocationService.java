package com.example.demo.services;

import com.example.demo.entities.Client;
import com.example.demo.entities.Document;
import com.example.demo.entities.Location;
import com.example.demo.repositories.LocationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@Service
public class LocationService {
    private LocationRepo locationRepo;
    private ClientService clientService;
    private DocService docService;
    public void addLocation(Long idDocument, String CIN) {
        Location location = new Location();

        location.setDateDebutLocation(new Date());

        // Set dateFinLocation to current date
        location.setDateFinLocation(new Date());

        // Add 7 days
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(location.getDateFinLocation());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        location.setDateFinLocation(calendar.getTime());

        location.setClient(clientService.getClientByCIN(CIN));

        Document document = docService.getDocsById(idDocument);
        location.setDocument(document);

        locationRepo.save(location);
        document.setNombreExemplaire(document.getNombreExemplaire() - 1);
        Client client = clientService.getClientByCIN(CIN);
        int nmbrAllocation = client.getNmbreAllocationEnCours()+1;

        client.setNmbreAllocationEnCours(nmbrAllocation);
        clientService.addClient(client);
        docService.saveDoc(document);
    }

    public Collection<Document> selectionDocsLouer(Long idClient){
        return locationRepo.selectionDocsLouer(idClient);
    }

    public void renduLocation(Long idDocument, Long idClient){
        Client client = clientService.getClientById(idClient);
        int nmbrAllocation = client.getNmbreAllocationEnCours() - 1;
        clientService.addClient(client);
        locationRepo.renduLocation(idDocument, idClient);
    }
    public Collection<Location> findAll(){
        return locationRepo.findAll();
    }
    public void saveLocation(Location location){
        locationRepo.save(location);
    }

    public double getAmende(Long idDocument, Long idClient){
        return locationRepo.getAmende(idDocument, idClient);
    }
}
