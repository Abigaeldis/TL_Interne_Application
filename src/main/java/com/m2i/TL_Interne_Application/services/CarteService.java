package com.m2i.TL_Interne_Application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Carte;
import com.m2i.TL_Interne_Application.repositories.CarteRepository;

@Service
public class CarteService {
	private final CarteRepository carteRepository;
	
	@Autowired
    public CarteService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }
    
    public Iterable<Carte> getAllCartes() {
        return carteRepository.findAll();
    }

    public Optional<Carte> getCarteById(int id) {
        return carteRepository.findById(id);
    }

    public Carte createCarte(Carte carte) throws BLLException {
    	BLLException blleException = new BLLException();

		if (carte.getNom().length() < 2) {
			blleException.ajouterErreur("Le nom de la carte doit faire au moins 2 caractères");
		}
		
		if (carte.getNom().length() > 30) {
			blleException.ajouterErreur("Le nom de la carte doit faire au maximum 30 caractères");
		}
		
		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
		
        return carteRepository.save(carte);
    }

    public Carte updateCarte(int id, Carte updatedCarte) throws BLLException {
		
    	BLLException blleException = new BLLException();

		if (updatedCarte.getNom().length() < 2) {
			blleException.ajouterErreur("Le nom de la carte doit faire au moins 2 caractères");
		}
		
		if (updatedCarte.getNom().length() > 30) {
			blleException.ajouterErreur("Le nom de la carte doit faire au maximum 30 caractères");
		}
		
		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
		
    	
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
