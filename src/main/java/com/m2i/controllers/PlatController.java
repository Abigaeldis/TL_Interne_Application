package com.m2i.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.entities.Plat;
import com.m2i.services.BLLException;
import com.m2i.services.PlatService;

@RestController
@RequestMapping("/plats")
public class PlatController {

	@Autowired
	private PlatService platService;

	@GetMapping
	public ResponseEntity<Iterable<Plat>> getAllPlats() {
		Iterable<Plat> plats = platService.getAllPlats();
		return new ResponseEntity<>(plats, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Plat> getPlatById(@PathVariable int id) {
		return platService.getPlatById(id).map(plat -> new ResponseEntity<>(plat, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Plat>> getAllPlatsForRestaurant(@PathVariable int restaurantId) {
		List<Plat> plats = platService.getAllPlatsForRestaurant(restaurantId);
		if (plats == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(plats, HttpStatus.OK);
	}

	@GetMapping("/type/{type}")
	public ResponseEntity<List<Plat>> getPlatsByTypeLike(@PathVariable String type) {
		List<Plat> plats = platService.findByTypeLike(type);
		if (plats.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(plats, HttpStatus.OK);
	}

	@GetMapping("/restaurant/{restaurantId}/type/{type}")
	public ResponseEntity<List<Plat>> getPlatsByTypeForRestaurant(@PathVariable int restaurantId,
			@PathVariable String type) {

		List<Plat> allPlats = platService.getAllPlatsForRestaurant(restaurantId);

		if (allPlats == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<Plat> platsByType = allPlats.stream().filter(plat -> plat.getType().equalsIgnoreCase(type))
				.collect(Collectors.toList());

		if (platsByType.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(platsByType, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createPlat(@RequestBody Plat plat) {
		Plat createdPlat;
		try {
			createdPlat = platService.createPlat(plat);
			return new ResponseEntity<>(createdPlat, HttpStatus.CREATED);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePlat(@PathVariable int id, @RequestBody Plat updatedPlat) {
		Plat updated;
		try {
			updated = platService.updatePlat(id, updatedPlat);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePlat(@PathVariable int id) {
		platService.deletePlat(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
