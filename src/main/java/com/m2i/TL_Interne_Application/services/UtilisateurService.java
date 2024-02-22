package com.m2i.TL_Interne_Application.services;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Utilisateur;
import com.m2i.TL_Interne_Application.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {
	@Autowired private UtilisateurRepository utilisateurRepo;
	
	public Iterable<Utilisateur> getAll() { 
		return utilisateurRepo.findAll(); 
		}
	
	public Utilisateur getById(int id) { 
		return utilisateurRepo.findById(id).get(); 
		}
	
	public void save(Utilisateur utilisateur) {
		utilisateurRepo.save(utilisateur); 
		}
	
	public void delete(int id) { 
		utilisateurRepo.deleteById(id); 
		}
	
	/*
	 * Quand on se connecte avec login et mdp, on crée un token aléatoire
	 * qu'on renverra à l'utilisateur pour ses accès futurs.
	 * Ici, le token est configuré pour expirer après 30 minutes d'inactivité
	 */
	public Utilisateur getByLoginAndPassword(String mail, String motDePasse) {
		Utilisateur user = utilisateurRepo.findByMailIsAndMotDePasseIs(mail, motDePasse);
		if (user != null) {
			user.setToken(generateToken());
			user.setExpirationTime(LocalDateTime.now().plusMinutes(30));
			utilisateurRepo.save(user);
		}
		return user;
	}
	
	/*
	 * Quand on s'identifie avec le token, on en profite pour mettre à jour
	 * la date d'expiration du token. Ainsi, tant que l'utilisateur est actif
	 * sur l'application, le token n'expire pas.
	 */
	public Utilisateur getByToken(String token) {
		Utilisateur user = utilisateurRepo.findByTokenIsAndExpirationTimeAfter(token, LocalDateTime.now());
		if (user != null) {
			user.setExpirationTime(LocalDateTime.now().plusMinutes(30));
			utilisateurRepo.save(user);
		}
		return user;
	}

	public void logout(String token) {
		Utilisateur user = utilisateurRepo.findByTokenIsAndExpirationTimeAfter(token, LocalDateTime.now());
		if (user != null) {
			user.setToken(null);
			user.setExpirationTime(null);
			utilisateurRepo.save(user);
		}
	}
	
	/*
	 * Les attributs static suivants et la méthode generateToken
	 * sont des outils nous permettant de générer un token aléatoire
	 * de 64 caractères de long.
	 */
	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Encoder base64encoder = Base64.getUrlEncoder();
	
	private String generateToken() {
		byte[] randomBytes = new byte[48];
		secureRandom.nextBytes(randomBytes);
		return base64encoder.encodeToString(randomBytes);
	}
}
