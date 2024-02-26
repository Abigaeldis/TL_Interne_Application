package com.m2i.TL_Interne_Application.controllers;

import java.time.LocalDateTime;
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

import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.entities.Table;
import com.m2i.TL_Interne_Application.services.BLLException;
import com.m2i.TL_Interne_Application.services.RestaurantService;
import com.m2i.TL_Interne_Application.services.TableService;

@RestController
@CrossOrigin
@RequestMapping("/tables")
public class TableController {
	@Autowired
	private TableService service;
	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public Iterable<Table> getAll() {
		return service.getAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Table> getById(@PathVariable("id") int id) {
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}

	@GetMapping(path = "/restaurant/{id}")
	public ResponseEntity<List<Table>> getByRestaurant(@PathVariable("id") int id) {
		return new ResponseEntity<>(service.findByRestaurant(restaurantService.getById(id)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Table table) {
		try {
			service.save(table);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (BLLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity<?> save(@PathVariable("id") int id, @RequestBody Table table) {
		try {
			service.update(id, table);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BLLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping ("/{id}/libre")
	public ResponseEntity<?> updateLibre(@PathVariable("id") int id) {
		Table table = service.getById(id);
		table.setEtat("Libre");
		try {
			service.update(id, table);
			return new ResponseEntity<>(table, HttpStatus.OK);
		} catch (BLLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping ("/{id}/reservee")
	public ResponseEntity<?> updateReservee(@PathVariable("id") int id) {
		Table table = service.getById(id);
		table.setEtat("Réservée");
		try {
			service.update(id, table);
			return new ResponseEntity<>(table, HttpStatus.OK);
		} catch (BLLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping ("/{id}/present")
	public ResponseEntity<?> updatePresent(@PathVariable("id") int id) {
		Table table = service.getById(id);
		table.setEtat("Présent");
		try {
			service.update(id, table);
			return new ResponseEntity<>(table, HttpStatus.OK);
		} catch (BLLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}
	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@DeleteMapping
	public ResponseEntity<Void> deleteById(@RequestBody Table table) {
		service.delete(table);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@GetMapping(path = "/restaurant/{id}/etat")
	public ResponseEntity<List<Table>> getByRestaurantAndStatut(@PathVariable("id") int id,
			@RequestParam("etat") String etat) {
		Restaurant restaurant = restaurantService.getById(id);
		return new ResponseEntity<>(service.findByRestaurantAndEtat(restaurant, etat), HttpStatus.OK);
	}
	
	@GetMapping(path = "/libres/{idresto}")
	public ResponseEntity<List<Table>> getTablesLibres(@PathVariable("idresto") int id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date, @RequestParam("nbPersonnes") int nbPersonnes) {
		Restaurant restaurant = restaurantService.getById(id);
		return new ResponseEntity<>(service.findTablesEligiblesReservation(restaurant, date, nbPersonnes), HttpStatus.OK);
	}
	
}
