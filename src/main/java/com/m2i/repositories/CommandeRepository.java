package com.m2i.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m2i.entities.Commande;
import com.m2i.entities.Restaurant;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {
	List<Commande> findByPlatCommandeIsNotEmpty();
	
	List<Commande> findByTableRestaurant(Restaurant restaurant);
	
	List<Commande> findByTableNumTableAndTableRestaurantIdAndStatut(int tableNum, int restaurantId, String statut);

}
