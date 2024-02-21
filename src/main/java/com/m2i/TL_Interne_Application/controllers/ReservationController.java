package com.m2i.TL_Interne_Application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.TL_Interne_Application.entities.Reservation;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.services.ReservationService;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
public class ReservationController {
	@Autowired
	private ReservationService service;
	
	@GetMapping
	public Iterable<Reservation> getAll(){
		return service.getAll();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Reservation> getById(@PathVariable("id") int id){
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Reservation reservation){
		service.saveOrUpdate(reservation);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id){
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteById(@RequestBody Reservation reservation){
		service.delete(reservation);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}