package com.m2i.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "utilisateurs")
@Data
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private String mail;
	@Column(name = "motdepasse")
	private String motDePasse;
	private String telephone;
	private String adresse;
	private String role;
	
	private String token;
	@Column(name = "expirationTime")
	private LocalDateTime expirationTime;

	@ManyToOne
	@JoinColumn(name = "id_restaurant")
	private Restaurant restaurant;

}
