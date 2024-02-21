package com.m2i.TL_Interne_Application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.services.RestaurantService;

@RestController
@CrossOrigin
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantService service;
	
	@GetMapping
	public Iterable<Restaurant> getAll(){
		return service.getAll();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Restaurant> getById(@PathVariable("id") int id){
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Restaurant restaurant){
		service.saveOrUpdate(restaurant);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
