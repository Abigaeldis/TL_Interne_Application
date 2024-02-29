package com.m2i.entities;

import java.util.List;

import lombok.Data;

@Data
public class AdditionWrapper {
	private List<PlatCommandeWrapper> listePlatsCommandes;
	private float sommeAddition;
	
	public AdditionWrapper() {
	}
	
	public AdditionWrapper(List<PlatCommandeWrapper> listePlatsCommandes, float sommeAddition) {
		super();
		this.listePlatsCommandes = listePlatsCommandes;
		this.sommeAddition = sommeAddition;
	}

	@Override
	public String toString() {
		return "AdditionWrapper [listePlatsCommandes=" + listePlatsCommandes + ", sommeAddition=" + sommeAddition + "]";
	}
}
