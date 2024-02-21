package com.m2i.TL_Interne_Application.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "restaurants")
@Data
public class Restaurant {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String adresse;
	private String status;
	@ManyToOne()
	@JoinColumn(name = "id_carte")
	private Carte carte;
}
