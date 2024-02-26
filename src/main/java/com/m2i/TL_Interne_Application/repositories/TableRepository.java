package com.m2i.TL_Interne_Application.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.entities.Table;

public interface TableRepository extends CrudRepository<Table, Integer>{
	List<Table> findByRestaurant(Restaurant restaurant);
	
	List<Table> findByRestaurantAndCapaciteTableGreaterThanEqual(Restaurant restaurant, int nbPersonnes);
	
	List<Table> findByRestaurantAndEtat(Restaurant restaurant, String etat);
}
