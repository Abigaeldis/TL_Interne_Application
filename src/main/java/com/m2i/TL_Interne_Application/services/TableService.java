package com.m2i.TL_Interne_Application.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Reservation;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.entities.Table;
import com.m2i.TL_Interne_Application.repositories.ReservationRepository;
import com.m2i.TL_Interne_Application.repositories.TableRepository;

@Service
public class TableService {
	@Autowired
	private TableRepository tableRepository;
	@Autowired
	private ReservationRepository reservationRepository;

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
			return tableRepository.findByRestaurantAndEtat(restaurant, etat);
		}
	}
	
	public List<Table> findTablesEligiblesReservation(Restaurant restaurant, LocalDateTime date, int nbPersonnes) {
		List<Table> tablesResto = tableRepository.findByRestaurantAndCapaciteTableGreaterThanEqual(restaurant, nbPersonnes);
		List<Table> tablesDispo = new ArrayList<>();
		LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
		LocalDateTime endOfDay = date.toLocalDate().atTime(LocalTime.MAX);
		LocalTime heureDemandeResa = date.toLocalTime();
		List<Reservation> reservationsRestoACetteDate = reservationRepository.findByDateBetweenOrderByDate(startOfDay, endOfDay);
		System.out.println("Demande résa : " + heureDemandeResa);
		
		//On regarde les tables occupées en fonction des résa dans ce resto à cette date
		List<Table> tablesOccupees = new ArrayList<>();
		for (Reservation reservation : reservationsRestoACetteDate) {
			LocalTime heureResa = reservation.getDate().toLocalTime();
			if (heureDemandeResa.isAfter(heureResa) && heureDemandeResa.isBefore(heureResa.plusMinutes(150))) {
				tablesOccupees.add(reservation.getTable());
			}
		}
		
		//On récupère les tables éligibles à la résa
		for (Table table : tablesResto) {
			if (!tablesOccupees.contains(table)) {
				tablesDispo.add(table);
			}
		}
		return tablesDispo;
	}
}
