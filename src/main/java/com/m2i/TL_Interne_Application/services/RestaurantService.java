package com.m2i.TL_Interne_Application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.repositories.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public Iterable<Restaurant> getAll(){
		return restaurantRepository.findAll();
	}
	
	public Restaurant getById(int id) {
		return restaurantRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Restaurant restaurant) {
		restaurantRepository.save(restaurant);
	}
}
