package com.m2i.TL_Interne_Application.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.TL_Interne_Application.entities.Message;
import com.m2i.TL_Interne_Application.entities.Restaurant;

public interface MessageRepository extends CrudRepository<Message, Integer> {
	List<Message> findByRestaurant(Restaurant restaurant);
}
