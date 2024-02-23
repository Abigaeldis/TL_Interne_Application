package com.m2i.TL_Interne_Application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Horaire;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.repositories.HoraireRepository;

@Service
public class HoraireService {
	@Autowired
	private HoraireRepository horaireRepository;
	
	public Iterable<Horaire> getAll() {
		return horaireRepository.findAll();
	}
	
	public Horaire getById(int id) {
		return horaireRepository.findById(id).get();
	}

	public void save(Horaire horaire) {
		horaireRepository.save(horaire);
	}

	public void delete(int id ) {
		horaireRepository.deleteById(id);
	}
	
	public List<Horaire> findByRestaurant(Restaurant restaurant) {
		return horaireRepository.findByRestaurant(restaurant);
	}
}
