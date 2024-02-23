package com.m2i.TL_Interne_Application.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@jakarta.persistence.Table(name = "commandes")
@Data
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String statut;
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name = "id_table")
	private Table table;

	@ManyToOne
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_commande")
	private List<PlatCommande> platCommande;
}