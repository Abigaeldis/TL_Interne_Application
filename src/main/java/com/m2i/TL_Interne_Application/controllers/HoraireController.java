package com.m2i.TL_Interne_Application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.m2i.TL_Interne_Application.entities.Horaire;
import com.m2i.TL_Interne_Application.services.HoraireService;
import com.m2i.TL_Interne_Application.services.RestaurantService;

@RestController
@CrossOrigin
@RequestMapping("/horaires")
public class HoraireController {
	@Autowired
	private HoraireService horaireService;
	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public Iterable<Horaire> getAll() {
		return horaireService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Horaire> getById(@PathVariable int id) {
		Horaire horaire = horaireService.getById(id);
		return new ResponseEntity<>(horaire, HttpStatus.OK);
	}

	@GetMapping(path = "/restaurant/{id}")
	public ResponseEntity<List<Horaire>> getByRestaurant(@PathVariable("id") int id){
		return new ResponseEntity<>(horaireService.findByRestaurant(restaurantService.getById(id)), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Horaire> insert(@RequestBody Horaire horaire) {
		horaireService.save(horaire);
		return new ResponseEntity<>(horaire, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Horaire horaire) {
		horaire.setId(id);
		horaireService.save(horaire);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Horaire> delete(@PathVariable("id") int id) {
		Horaire horaire = horaireService.getById(id);
		horaireService.delete(id);
		return new ResponseEntity<>(horaire, HttpStatus.OK);
	}

}
