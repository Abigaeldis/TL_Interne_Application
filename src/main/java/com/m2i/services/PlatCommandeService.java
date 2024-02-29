package com.m2i.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.entities.PlatCommande;
import com.m2i.repositories.PlatCommandeRepository;

@Service
public class PlatCommandeService {
    private PlatCommandeRepository platCommandeRepository;

    @Autowired
    public PlatCommandeService(PlatCommandeRepository platCommandeRepository) {
    	this.platCommandeRepository = platCommandeRepository;
    }
    
    public Iterable<PlatCommande> getAllPlatCommandes() {
        return platCommandeRepository.findAll();
    }

    public Optional<PlatCommande> getPlatCommandeById(int id) {
        return platCommandeRepository.findById(id);
    }

    public PlatCommande createPlatCommande(PlatCommande platCommande) {
        return platCommandeRepository.save(platCommande);
    }

    public PlatCommande updatePlatCommande(int id, PlatCommande updatedPlatCommande) {
        if (platCommandeRepository.existsById(id)) {
            updatedPlatCommande.setId(id);
            return platCommandeRepository.save(updatedPlatCommande);
        } else {
            return null;
        }
    }

    public void deletePlatCommande(int id) {
        platCommandeRepository.deleteById(id);
    }
}
