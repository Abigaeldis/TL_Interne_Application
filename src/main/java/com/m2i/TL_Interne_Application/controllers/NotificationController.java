package com.m2i.TL_Interne_Application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.TL_Interne_Application.entities.Message;
import com.m2i.TL_Interne_Application.entities.NotificationWrapper;
import com.m2i.TL_Interne_Application.entities.Reservation;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.services.MessageService;
import com.m2i.TL_Interne_Application.services.ReservationService;
import com.m2i.TL_Interne_Application.services.RestaurantService;

@RestController
@CrossOrigin
@RequestMapping("/notifications")
public class NotificationController {
	@Autowired private MessageService messageService;
	@Autowired private ReservationService reservationService;
	@Autowired private RestaurantService restaurantService;
	
	@GetMapping
	public ResponseEntity<NotificationWrapper> getAll() {
		Iterable<Message> messagesRecus = messageService.getAll();
		Iterable<Reservation> reservationsEnAttente = reservationService.getAll();
		NotificationWrapper notifications = new NotificationWrapper(messagesRecus, reservationsEnAttente);
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NotificationWrapper> getAllByRestaurant(@PathVariable int id) {
		Restaurant restaurant = restaurantService.getById(id);
		Iterable<Message> messagesRecus = messageService.findByRestaurant(restaurant);
		Iterable<Reservation> reservationsEnAttente = reservationService.findByRestaurantAndStatut(restaurant, "En attente");
		NotificationWrapper notifications = new NotificationWrapper(messagesRecus, reservationsEnAttente);
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}
}
