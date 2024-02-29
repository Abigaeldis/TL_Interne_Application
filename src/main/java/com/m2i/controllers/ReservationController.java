package com.m2i.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.entities.Reservation;
import com.m2i.services.BLLException;
import com.m2i.services.ReservationService;
import com.m2i.services.RestaurantService;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
public class ReservationController {
	private ReservationService service;
	private RestaurantService restaurantService;

	@Autowired
	public ReservationController(ReservationService service, RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
		this.service = service;
	}
	
	@GetMapping
	public Iterable<Reservation> getAll() {
		return service.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Reservation> getById(@PathVariable("id") int id) {
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/restaurant/{id}")
	public ResponseEntity<List<Reservation>> getByRestaurant(@PathVariable("id") int id){
		return new ResponseEntity<>(service.findByRestaurant(restaurantService.getById(id)), HttpStatus.OK);
	}

	@GetMapping(path = "/par-date")
	public ResponseEntity<List<Reservation>> getAllReservationsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		System.out.println(date);
		LocalDateTime startOfDay = date.atStartOfDay();
		LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
		System.out.println(startOfDay);
		System.out.println(endOfDay);
		List<Reservation> reservations = service.getAllReservationsByDate(startOfDay, endOfDay);
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}

	@GetMapping(path = "/numTables/par-date")
	public ResponseEntity<List<Integer>> getNuMTablesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
	    System.out.println(date);
	    LocalDateTime startOfDay = date.atStartOfDay();
	    LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
	    
	    List<Reservation> reservations = service.getAllReservationsByDate(startOfDay, endOfDay);
	    List<Integer> numerosTables = new ArrayList<>();
	    
	    for (Reservation reservation : reservations) {
	    	numerosTables.add(reservation.getTable().getNumTable());
	    }

	    return new ResponseEntity<>(numerosTables, HttpStatus.OK);
	}
	
	
	@PostMapping 
	public ResponseEntity<?> save(@RequestBody Reservation reservation) {
		try {
			service.save(reservation);
			return new ResponseEntity<>(reservation, HttpStatus.CREATED);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Reservation reservation) {
		try {
			service.update(id, reservation);
			return new ResponseEntity<>(reservation, HttpStatus.OK);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteById(@RequestBody Reservation reservation) {
		service.delete(reservation);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
