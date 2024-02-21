package com.m2i.TL_Interne_Application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Commande;
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
}