package com.m2i.TL_Interne_Application.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.entities.Table;
import com.m2i.TL_Interne_Application.repositories.TableRepository;

@Service
public class TableService {
	@Autowired
	private TableRepository tableRepository;

	public Iterable<Table> getAll() {
		return tableRepository.findAll();
	}

	public Table getById(int id) {
		return tableRepository.findById(id).get();
	}

	public void save(Table table) throws BLLException {

		BLLException blleException = new BLLException();

		List<String> valeursValides = Arrays.asList("Libre", "Occupée", "Occupee", "Réservée", "Reservee");

		if (table.getCapaciteTable() < 1) {
			blleException.ajouterErreur("La table doit pouvoir accueillir au moins 1 personne");
		}

		if (!valeursValides.contains(table.getEtat())) {
			blleException.ajouterErreur("L'état de la table doit valoir : Libre, Occupée ou Réservée");
		}

		if (table.getRestaurant() == null) {
			blleException.ajouterErreur("La table doit être associée à un restaurant");
		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
		
		tableRepository.save(table);
	}
	
	public void update(int id, Table table) throws BLLException {

		BLLException blleException = new BLLException();
		
		table.setId(id);

		List<String> valeursValides = Arrays.asList("Libre", "Occupée", "Occupee", "Réservée", "Reservee");

		if (table.getCapaciteTable() < 1) {
			blleException.ajouterErreur("La table doit pouvoir accueillir au moins 1 personne");
		}

		if (!valeursValides.contains(table.getEtat())) {
			blleException.ajouterErreur("L'état de la table doit valoir : Libre, Occupée ou Réservée");
		}

		if (table.getRestaurant() == null) {
			blleException.ajouterErreur("La table doit être associée à un restaurant");
		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
		
		tableRepository.save(table);
	}
	
	

	public void delete(Table table) {
		tableRepository.delete(table);
	}

	public void deleteById(int id) {
		tableRepository.deleteById(id);
	}

	public List<Table> findByRestaurant(Restaurant restaurant) {
		return tableRepository.findByRestaurant(restaurant);
	}

	public List<Table> findByRestaurantAndEtat(Restaurant restaurant, String etat) {
		List<String> validValues = Arrays.asList("Libre", "Occupée", "Occupee", "Réservée", "Reservee");

		if (!validValues.contains(etat)) {
			return null;
		} else {
			System.out.println("etat ok");
			return tableRepository.findByRestaurantAndEtat(restaurant, etat);
		}
	}
}
