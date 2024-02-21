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

@Entity
@Table(name = "messages")
@Data
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	@Column(name = "corps_message")
	private String corpsMessage;

	@ManyToOne
	@JoinColumn(name = "id_restaurant")
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;

}
