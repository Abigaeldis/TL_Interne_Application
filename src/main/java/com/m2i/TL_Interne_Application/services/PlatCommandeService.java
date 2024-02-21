package com.m2i.TL_Interne_Application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.PlatCommande;
import com.m2i.TL_Interne_Application.repositories.PlatCommandeRepository;

@Service
public class PlatCommandeService {

    @Autowired
    private PlatCommandeRepository platCommandeRepository;

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
            // Handle error, throw exception, etc.
            return null;
        }
    }

    public void deletePlatCommande(int id) {
        platCommandeRepository.deleteById(id);
    }
}
