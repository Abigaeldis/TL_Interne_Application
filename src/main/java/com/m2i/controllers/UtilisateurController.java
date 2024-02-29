package com.m2i.controllers;

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

import com.m2i.entities.Utilisateur;
import com.m2i.services.BLLException;
import com.m2i.services.UtilisateurService;

@RestController
@CrossOrigin
@RequestMapping("/utilisateurs")
public class UtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping
	public Iterable<Utilisateur> getAll() {
		return utilisateurService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Utilisateur> getById(@PathVariable int id) {
		Utilisateur utilisateur = utilisateurService.getById(id);
		return new ResponseEntity<>(utilisateur, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Utilisateur utilisateur) {
		try {
			utilisateurService.save(utilisateur);
			return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Utilisateur utilisateur) {
		try {
			utilisateurService.update(id, utilisateur);
			return new ResponseEntity<>(utilisateur, HttpStatus.OK);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Utilisateur> delete(@PathVariable("id") int id) {
		Utilisateur utilisateur = utilisateurService.getById(id);
		utilisateurService.delete(id);
		return new ResponseEntity<>(utilisateur, HttpStatus.OK);
	}

}
