package com.m2i.TL_Interne_Application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Reservation;
import com.m2i.TL_Interne_Application.repositories.ReservationRepository;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	
	public Iterable<Reservation> getAll(){
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
}
