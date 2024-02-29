package com.m2i.repositories;

import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;

import com.m2i.entities.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
	/*
	 * Nous permettra de retrouver un utilisateur d'après son token d'identification,
	 * à condition que le token n'ait pas expiré.
	 * Cette méthode est utilisée pour retrouver un utilisateur qui s'est déjà connecté.
	 */
	public Utilisateur findByTokenIsAndExpirationTimeAfter(String token, LocalDateTime expirationTime);
	
	/*
	 * Nous permettra de retrouver un utilisateur d'après son identifiant / mdp
	 * Cette méthode est utilisée pour trouver un utilisateur au moment de la connexion.
	 */
	public Utilisateur findByMailIsAndMotDePasseIs(String mail, String motDePasse);
}
