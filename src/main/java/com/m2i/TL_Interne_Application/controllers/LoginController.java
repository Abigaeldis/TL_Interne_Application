package com.m2i.TL_Interne_Application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.TL_Interne_Application.entities.Utilisateur;
import com.m2i.TL_Interne_Application.services.UtilisateurService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
	@Autowired private UtilisateurService service;
	
	/*
	 * Endpoint utilisé pour authentifier un utilisateur au moment du login.
	 * Renvoie une erreur 401 "Unauthorized" si le couple identifiant / mdp est faux
	 * Renvoie un user avec son token si la connexion réussit
	 */
	@PostMapping
	public ResponseEntity<Utilisateur> get(@RequestParam String mail, @RequestParam String motDePasse) {
		Utilisateur user = service.getByLoginAndPassword(mail, motDePasse);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}
}
