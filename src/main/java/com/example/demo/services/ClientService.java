package com.example.demo.services;

import com.example.demo.entities.Client;
import com.example.demo.repositories.ClientRepo;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepo clientRepo;
    public Collection<Client> getAllClients(){
        return clientRepo.findAll();
    }
    public Client getClientByCIN(String cin){
        return clientRepo.getClientByCIN(cin);
    }
    public void addClient(Client client){
        Faker faker = new Faker();
        client.setCIN(faker.idNumber().valid());
        clientRepo.save(client);
    }

    public Client getClientById(Long id){
        return clientRepo.findById(id).get();
    }

    public void deleteClientById(Long id){
        clientRepo.deleteById(id);
    }

    public Collection<Client> SearchClient(String term){
        return clientRepo.getClientLikeCin(term);
    }

    public Collection<Client> getClientLikeCin(String term){
        return clientRepo.getClientLikeCin(term);
    }
    public boolean isAdherent(Long id){
        Client client = clientRepo.findClientById(id);
        return client.getIsAdherent();
    }


}
