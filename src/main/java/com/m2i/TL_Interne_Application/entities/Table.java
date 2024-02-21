package com.m2i.TL_Interne_Application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity @jakarta.persistence.Table(name = "tables")
@Data
public class Table {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "num_table")
	private int numTable;
	@Column(name = "capacite_table")
	private int capaciteTable;
	private String etat;
	@ManyToOne
	@JoinColumn(name = "id_restaurant")
	private Restaurant restaurant;
}
