package com.m2i.TL_Interne_Application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Carte;
import com.m2i.TL_Interne_Application.repositories.CarteRepository;

@Service
public class CarteService {

    @Autowired
    private CarteRepository carteRepository;

    public Iterable<Carte> getAllCartes() {
        return carteRepository.findAll();
    }

    public Optional<Carte> getCarteById(int id) {
        return carteRepository.findById(id);
    }

    public Carte createCarte(Carte carte) {
        return carteRepository.save(carte);
    }

    public Carte updateCarte(int id, Carte updatedCarte) {
        if (carteRepository.existsById(id)) {
            updatedCarte.setId(id);
            return carteRepository.save(updatedCarte);
        } else {
            return null;
        }
    }

    public void deleteCarte(int id) {
        carteRepository.deleteById(id);
    }
}
