package com.m2i.TL_Interne_Application.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Horaire;
import com.m2i.TL_Interne_Application.entities.Reservation;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.repositories.ReservationRepository;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	HoraireService horaireService;

	public Iterable<Reservation> getAll() {
		return reservationRepository.findAll();
	}

	public Reservation getById(int id) {
		return reservationRepository.findById(id).get();
	}

	public void save(Reservation reservation) throws BLLException {

		BLLException blleException = new BLLException();
		if (reservation.getNbPersonne() < 1) {
			blleException.ajouterErreur("Veuillez choisir un nombre de personnes supérieur ou égal à 1.");
		}

		if (reservation.getDate().isBefore(LocalDateTime.now())) {
			blleException.ajouterErreur("Veuillez choisir une date à venir et non passée.");
		}

//		if (!validateHoraire(reservation.getDate(), reservation.getRestaurant(),
//				horaireService.findByRestaurant(reservation.getRestaurant()))) {
//			blleException.ajouterErreur(
//					"Veuillez choisir une date et un horaire de réservation parmi les horaires d'ouverture du magasin repris ci-dessous.");
//		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}

		reservationRepository.save(reservation);
	}

	public void update(int id, Reservation reservation) throws BLLException {

		BLLException blleException = new BLLException();
		
		reservation.setId(id);
		
		if (reservation.getNbPersonne() < 1) {
			blleException.ajouterErreur("Veuillez choisir un nombre de personnes supérieur ou égal à 1.");
		}

		if (reservation.getDate().isBefore(LocalDateTime.now())) {
			blleException.ajouterErreur("Veuillez choisir une date à venir et non passée.");
		}

//		if (!validateHoraire(reservation.getDate(), reservation.getRestaurant(),
//				horaireService.findByRestaurant(reservation.getRestaurant()))) {
//			blleException.ajouterErreur(
//					"Veuillez choisir une date et un horaire de réservation parmi les horaires d'ouverture du magasin repris ci-dessous.");
//		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}

		reservationRepository.save(reservation);
	}

	public void delete(Reservation reservation) {
		reservationRepository.delete(reservation);
	}

	public void deleteById(int id) {
		reservationRepository.deleteById(id);
	}

	public List<Reservation> getAllReservationsByDate(LocalDateTime startOfDay, LocalDateTime endOfDay) {
		return reservationRepository.findByDateBetweenOrderByDate(startOfDay, endOfDay);
	}
	
	public List<Reservation> getAllReservationsByRestaurantAndDate(LocalDateTime startOfDay, LocalDateTime endOfDay) {
		return reservationRepository.findByDateBetweenOrderByDate(startOfDay, endOfDay);
	}

	public List<Reservation> findByRestaurantAndStatut(Restaurant restaurant, String statut) {
		return reservationRepository.findByRestaurantAndStatut(restaurant, statut);
	}

	public List<Reservation> findByRestaurant(Restaurant restaurant) {
		return reservationRepository.findByRestaurant(restaurant);
	}

	public boolean validateHoraire(LocalDateTime date, Restaurant restaurant, List<Horaire> horaires) {
		List<Horaire> horairesRestaurant = new ArrayList<>();

		for (Horaire current : horaires) {
			if (current.getRestaurant().getNom().equals(restaurant.getNom())) {
				horairesRestaurant.add(current);
			}
		}

		String[] jours = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };
		int jourInt = date.getDayOfWeek().getValue();
		String jourReservation = jours[jourInt - 1];

		LocalTime horaireReservation = date.toLocalTime();

		for (Horaire current : horairesRestaurant) {
			String jour = current.getJour();
			if (jourReservation.equals(jour)) {
				LocalTime heureDebut = current.getHeureDeDebut();
				LocalTime heureFin = current.getHeureDeFin();
				if (horaireReservation.isAfter(heureDebut) && horaireReservation.isBefore(heureFin)) {
					return true;
				}
			}
		}
		return false;
	}
}
