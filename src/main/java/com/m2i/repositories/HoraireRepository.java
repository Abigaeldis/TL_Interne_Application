package com.m2i.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.entities.Horaire;
import com.m2i.entities.Restaurant;

public interface HoraireRepository extends CrudRepository<Horaire, Integer> {
	
	List<Horaire> findByRestaurant(Restaurant restaurant);

}
