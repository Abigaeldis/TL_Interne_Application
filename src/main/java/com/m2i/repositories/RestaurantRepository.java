package com.m2i.repositories;

import org.springframework.data.repository.CrudRepository;

import com.m2i.entities.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{}
