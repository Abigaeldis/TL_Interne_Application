package com.m2i.TL_Interne_Application.services;

import java.util.ArrayList;
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
	
	public void save(Commande commande) {
		commandeRepo.save(commande); 
		}
	
	public void delete(int id) { 
		commandeRepo.deleteById(id); 
		}
	
	public List<Commande> findByPlatCommandeIsNotEmpty() {
        return commandeRepo.findByPlatCommandeIsNotEmpty();
    }

    public float getTotalPriceOfCommande(Commande commande) {
    	float totalPrice = (float) 0.0;
        for (PlatCommande platCommande : commande.getPlatCommande()) {
            totalPrice += platCommande.getPlat().getPrix() * platCommande.getNbPlat();
        }
        return totalPrice;
    }
    
    public List<PlatCommandeWrapper> getListeAddition(Commande commande) {
        List<PlatCommandeWrapper> resultat = new ArrayList<>();
        for (PlatCommande platCommande : commande.getPlatCommande()) {
        	resultat.add(new PlatCommandeWrapper(platCommande));
        }
        return resultat;
    }
    
    public List<Commande> getByRestaurant(Restaurant restaurant) {
    	return commandeRepo.findByTableRestaurant(restaurant);
    }
}