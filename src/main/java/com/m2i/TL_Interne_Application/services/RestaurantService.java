package com.m2i.TL_Interne_Application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.repositories.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;

	public Iterable<Restaurant> getAll() {
		return restaurantRepository.findAll();
	}

	public Restaurant getById(int id) {
		return restaurantRepository.findById(id).get();
	}

	public void saveOrUpdate(Restaurant restaurant) throws BLLException {

		BLLException blleException = new BLLException();

		if (restaurant.getNom().length() < 2) {
			blleException.ajouterErreur("Le nom doit faire au moins 2 caractères");
		}

		if (restaurant.getNom().length() > 30) {
			blleException.ajouterErreur("Le nom doit faire au maximum 30 caractères");
		}

		if (restaurant.getAdresse().length() < 2) {
			blleException.ajouterErreur("L'adresse doit faire au moins 2 caractères");
		}

		if (restaurant.getAdresse().length() > 150) {
			blleException.ajouterErreur("L'adresse doit faire au maximum 150 caractères");
		}

		if (restaurant.getDescription().length() < 2) {
			blleException.ajouterErreur("La description doit faire au moins 2 caractères");
		}

		if (restaurant.getDescription().length() > 300) {
			blleException.ajouterErreur("La description doit faire au maximum 300 caractères");
		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}

		restaurantRepository.save(restaurant);
	}
}
