package com.example.demo.metier;
import com.example.demo.entities.*;
import com.example.demo.repositories.*;
public interface BibliothequeService {
    public Client ajouterClient(Client client);
    public void supprimerClient(Long idClient);
    Client modifierClient(Long idClient, Client client);
    public Client afficherClient(Long idClient);

    public Document ajouterDocument(Document document);
    public void supprimerDocument(Long idDocument);
    public Document modifierDocument(Document document);
    public Document afficherDocument(Long idDocument);
    public Location louer(Client client, Document document);
    public Pret preter(Adherent adherent, Document document);
    public Reservation reserver(Client client, Document document);
}
