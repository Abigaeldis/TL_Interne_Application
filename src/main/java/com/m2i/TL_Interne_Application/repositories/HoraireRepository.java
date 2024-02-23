package com.m2i.TL_Interne_Application.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.TL_Interne_Application.entities.Horaire;
import com.m2i.TL_Interne_Application.entities.Restaurant;

public interface HoraireRepository extends CrudRepository<Horaire, Integer> {
	
	List<Horaire> findByRestaurant(Restaurant restaurant);

}