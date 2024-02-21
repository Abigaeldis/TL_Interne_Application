package com.m2i.TL_Interne_Application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Utilisateur;
import com.m2i.TL_Interne_Application.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {
	@Autowired private UtilisateurRepository utilisateurRepo;
	
	public Iterable<Utilisateur> getAll() { 
		return utilisateurRepo.findAll(); 
		}
	
	public Utilisateur getById(int id) { 
		return utilisateurRepo.findById(id).get(); 
		}
	
	public void save(Utilisateur utilisateur) {
		utilisateurRepo.save(utilisateur); 
		}
	
	public void delete(int id) { 
		utilisateurRepo.deleteById(id); 
		}
}
