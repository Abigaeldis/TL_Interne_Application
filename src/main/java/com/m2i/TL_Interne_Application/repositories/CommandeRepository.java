package com.m2i.TL_Interne_Application.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.TL_Interne_Application.entities.Commande;
import com.m2i.TL_Interne_Application.entities.Restaurant;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {
	List<Commande> findByPlatCommandeIsNotEmpty();
	
	List<Commande> findByTableRestaurant(Restaurant restaurant);
}
