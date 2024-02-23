package com.m2i.TL_Interne_Application.entities;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "horaires")
@Data
public class Horaire {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String jour;
	private LocalTime heureDeDebut;
	private LocalTime heureDeFin;
	private String creneau;
	@ManyToOne
	@JoinColumn(name = "id_restaurant")
	private Restaurant restaurant;
	
	

}
