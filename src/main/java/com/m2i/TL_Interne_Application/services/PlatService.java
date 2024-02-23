package com.m2i.TL_Interne_Application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Carte;
import com.m2i.TL_Interne_Application.entities.Plat;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.repositories.PlatRepository;
import com.m2i.TL_Interne_Application.repositories.RestaurantRepository;

@Service
public class PlatService {

	@Autowired
	private PlatRepository platRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	public Iterable<Plat> getAllPlats() {
		return platRepository.findAll();
	}

	public Optional<Plat> getPlatById(int id) {
		return platRepository.findById(id);
	}

	public List<Plat> getAllPlatsForRestaurant(int restaurantId) {
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
		if (optionalRestaurant.isEmpty()) {
			return null;
		}
		Restaurant restaurant = optionalRestaurant.get();

		Carte carte = restaurant.getCarte();
		if (carte == null) {
			return null;
		}
		return platRepository.findByCarte(carte);
	}

	public List<Plat> findByTypeLike(String type) {
		return platRepository.findByTypeLike(type);
	}

	public Plat createPlat(Plat plat) throws BLLException {

		BLLException blleException = new BLLException();

		if (plat.getNom().length() < 2) {
			blleException.ajouterErreur("Le nom du plat doit faire au moins 2 caractères");
		}

		if (plat.getNom().length() > 30) {
			blleException.ajouterErreur("Le nom du plat doit faire au maximum 30 caractères");
		}

		if (plat.getDescription().length() < 2) {
			blleException.ajouterErreur("La description du plat doit faire au moins 2 caractères");
		}

		if (plat.getDescription().length() > 150) {
			blleException.ajouterErreur("La description du plat doit faire au maximum 150 caractères");
		}

		if (plat.getType().length() < 2) {
			blleException.ajouterErreur("La type doit du plat faire au moins 2 caractères");
		}

		if (plat.getType().length() > 15) {
			blleException.ajouterErreur("La type du plat doit faire au maximum 15 caractères");
		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
		return platRepository.save(plat);
	}
	
	

	public Plat updatePlat(int id, Plat updatedPlat) throws BLLException {
		
		BLLException blleException = new BLLException();

		if (updatedPlat.getNom().length() < 2) {
			blleException.ajouterErreur("Le nom du plat doit faire au moins 2 caractères");
		}

		if (updatedPlat.getNom().length() > 30) {
			blleException.ajouterErreur("Le nom du plat doit faire au maximum 30 caractères");
		}

		if (updatedPlat.getDescription().length() < 2) {
			blleException.ajouterErreur("La description du plat doit faire au moins 2 caractères");
		}

		if (updatedPlat.getDescription().length() > 150) {
			blleException.ajouterErreur("La description du plat doit faire au maximum 150 caractères");
		}

		if (updatedPlat.getType().length() < 2) {
			blleException.ajouterErreur("La type doit du plat faire au moins 2 caractères");
		}

		if (updatedPlat.getType().length() > 15) {
			blleException.ajouterErreur("La type du plat doit faire au maximum 15 caractères");
		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
		updatedPlat.setId(id);

		return platRepository.save(updatedPlat);

	}

	public void deletePlat(int id) {
		platRepository.deleteById(id);
	}

}
