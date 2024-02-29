package com.m2i.entities;

import lombok.Data;

@Data
public class PlatCommandeWrapper {
	private int nbPlat;
	private String nom;
	private float prixTot;
	
	public PlatCommandeWrapper() {
	}

	public PlatCommandeWrapper(PlatCommande platCommande) {
		this.nbPlat = platCommande.getNbPlat();
		this.nom = platCommande.getPlat().getNom();
		this.prixTot = platCommande.getPlat().getPrix() * nbPlat;
	}

	@Override
	public String toString() {
		return "PlatCommandeWrapper [nbPlat=" + nbPlat + ", nom=" + nom + ", prixTot=" + prixTot + "]";
	}
}
