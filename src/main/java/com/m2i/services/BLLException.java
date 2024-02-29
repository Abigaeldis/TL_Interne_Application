package com.m2i.services;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private List<String> erreurs = new ArrayList<>();

	public BLLException() {
	}

	public BLLException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BLLException(String message) {
		super(message);
	}
	
	public void ajouterErreur(String erreur) {
		erreurs.add(erreur);
	}
	
	public List<String> getErreurs() {
		return erreurs;
	}
}
