package com.m2i.entities;

import java.time.LocalTime;

import jakarta.persistence.Column;
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
	@Column(name = "heurededebut")
	private LocalTime heureDeDebut;
	@Column(name = "heuredefin")
	private LocalTime heureDeFin;
	private String creneau;
	@ManyToOne
	@JoinColumn(name = "id_restaurant")
	private Restaurant restaurant;
	
	

}
