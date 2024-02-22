package com.m2i.TL_Interne_Application.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Reservation;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.repositories.ReservationRepository;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;

	public Iterable<Reservation> getAll() {
		return reservationRepository.findAll();
	}

	public Reservation getById(int id) {
		return reservationRepository.findById(id).get();
	}

	public void saveOrUpdate(Reservation reservation) {
		reservationRepository.save(reservation);
	}

	public void delete(Reservation reservation) {
		reservationRepository.delete(reservation);
	}

	public void deleteById(int id) {
		reservationRepository.deleteById(id);
	}

	public List<Reservation> getAllReservationsByDate(LocalDateTime startOfDay, LocalDateTime endOfDay) {
		return reservationRepository.findByDateBetween(startOfDay, endOfDay);
	}

	public List<Reservation> findByRestaurantAndStatut(Restaurant restaurant, String statut){
		return reservationRepository.findByRestaurantAndStatut(restaurant, statut);
	}
}


