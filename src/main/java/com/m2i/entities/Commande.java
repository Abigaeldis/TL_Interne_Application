package com.m2i.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_table")
	private Table table;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;

	@OneToMany(mappedBy = "commande", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PlatCommande> platCommande;
}