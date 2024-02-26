package com.m2i.TL_Interne_Application.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Commande;
import com.m2i.TL_Interne_Application.entities.PlatCommande;
import com.m2i.TL_Interne_Application.entities.PlatCommandeWrapper;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.repositories.CommandeRepository;

@Service
public class CommandeService {
	@Autowired private CommandeRepository commandeRepo;
	
	public Iterable<Commande> getAll() { 
		return commandeRepo.findAll(); 
		}
	
	public Commande getById(int id) { 
		return commandeRepo.findById(id).get(); 
		}
	
	public Commande save(Commande commande) throws BLLException {
		BLLException blleException = new BLLException();
		System.out.println(commande.getDate().toLocalDate());
		System.out.println(LocalDate.now());
		
//		if (!commande.getDate().toLocalDate().isEqual(LocalDate.now())) {
//			blleException.ajouterErreur("La date de la commande doit être à aujourd'hui.");
//		}
		
		if (commande.getPlatCommande().isEmpty()) {
			blleException.ajouterErreur("Vous ne pouvez pas enregistrer une commande vide.");
		}
		
		if (commande.getTable() == null) {
			blleException.ajouterErreur("Veuillez affecter la commande à une table.");
		}

		List<String> valeursValides = Arrays.asList("LANCEE", "PRETE", "SERVIE", "REGLEE");
		if (!valeursValides.contains(commande.getStatut().toUpperCase())) {
			blleException.ajouterErreur("L'état de la commande doit valoir : lancée, prête, servie ou réglée");
		}
		
		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
		
		for (PlatCommande current : commande.getPlatCommande()) {
			current.setCommande(commande);
		}
		
		commandeRepo.save(commande); 
		return commande;
	}
	
	public void delete(int id) { 
		commandeRepo.deleteById(id); 
		}
	
	public List<Commande> findByPlatCommandeIsNotEmpty() {
        return commandeRepo.findByPlatCommandeIsNotEmpty();
    }

    public float getTotalPriceOfCommande(Commande commande) throws BLLException {
    	float totalPrice = (float) 0.0;
        for (PlatCommande platCommande : commande.getPlatCommande()) {
            totalPrice += platCommande.getPlat().getPrix() * platCommande.getNbPlat();
        }
        if (totalPrice != 0) {
        	return totalPrice;
        } else {
        	throw new BLLException("La commande sélectionnée est vide");
        }
    }
    
    public List<PlatCommandeWrapper> getListeAddition(Commande commande) throws BLLException {
        List<PlatCommandeWrapper> resultat = new ArrayList<>();
        for (PlatCommande platCommande : commande.getPlatCommande()) {
        	resultat.add(new PlatCommandeWrapper(platCommande));
        }
        if (!resultat.isEmpty()) {
        	return resultat;
        } else {
        	throw new BLLException("La commande sélectionnée est vide");
        }
    }
    
    public List<Commande> getByRestaurant(Restaurant restaurant) {
    	return commandeRepo.findByTableRestaurant(restaurant);
    }
}