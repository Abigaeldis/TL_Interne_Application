package com.m2i.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.entities.Reservation;
import com.m2i.entities.Restaurant;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

	List<Reservation> findByDateBetweenOrderByDate(LocalDateTime startOfDay, LocalDateTime endOfDay);
	
	List<Reservation> findByRestaurantAndDateBetweenOrderByDate(Restaurant restaurant, LocalDateTime startOfDay, LocalDateTime endOfDay);

	List<Reservation> findByRestaurantAndStatut(Restaurant restaurant, String statut);

	List<Reservation> findByRestaurant(Restaurant restaurant);


}