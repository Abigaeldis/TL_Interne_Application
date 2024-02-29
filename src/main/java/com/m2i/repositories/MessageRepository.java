package com.m2i.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.entities.Message;
import com.m2i.entities.Restaurant;

public interface MessageRepository extends CrudRepository<Message, Integer> {
	List<Message> findByRestaurant(Restaurant restaurant);
}
