package com.m2i.TL_Interne_Application.controllers;

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

import com.m2i.TL_Interne_Application.entities.Commande;
import com.m2i.TL_Interne_Application.services.CommandeService;

@RestController
@CrossOrigin
@RequestMapping("/commandes")
public class CommandeController {
	@Autowired
	private CommandeService commandeService;

	@GetMapping
	public Iterable<Commande> getAll() {
		return commandeService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Commande> getById(@PathVariable int id) {
		Commande commande = commandeService.getById(id);
		return new ResponseEntity<>(commande, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Commande> insert(@RequestBody Commande commande) {
		commandeService.save(commande);
		return new ResponseEntity<>(commande, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Commande commande) {
		commande.setId(id);
		commandeService.save(commande);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Commande> delete(@PathVariable("id") int id) {
		Commande commande = commandeService.getById(id);
		commandeService.delete(id);
		return new ResponseEntity<>(commande, HttpStatus.OK);
	}

}