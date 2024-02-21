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

import com.m2i.TL_Interne_Application.entities.Utilisateur;
import com.m2i.TL_Interne_Application.services.UtilisateurService;

@RestController
@CrossOrigin
@RequestMapping("/utilisateur")
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
	public ResponseEntity<Utilisateur> insert(@RequestBody Utilisateur utilisateur) {
		utilisateurService.save(utilisateur);
		return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Utilisateur utilisateur) {
		utilisateur.setId(id);
		utilisateurService.save(utilisateur);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Utilisateur> delete(@PathVariable("id") int id) {
		Utilisateur utilisateur = utilisateurService.getById(id);
		utilisateurService.delete(id);
		return new ResponseEntity<>(utilisateur, HttpStatus.OK);
	}

}
