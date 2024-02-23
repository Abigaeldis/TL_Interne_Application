package com.m2i.TL_Interne_Application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity @Table(name = "platscommandes")
@Data
public class PlatCommande {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nb_plat")
	private int nbPlat;
	
	@ManyToOne
	@JoinColumn(name = "id_plat")
	private Plat plat;
}

