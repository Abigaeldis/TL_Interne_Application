package com.m2i.TL_Interne_Application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.m2i.TL_Interne_Application.entities.Commande;
import com.m2i.TL_Interne_Application.entities.PlatCommandeWrapper;

@SpringBootTest
class CommandeServiceTest {
	@Autowired
	private CommandeService service;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Sql("classpath:table_insertion.sql")
	void getTotalPriceOfCommande_tableInsertionCommande1_Return23() {
		Commande commande = service.getById(1);
		float prixCommande =0f;
		try {
			prixCommande = service.getTotalPriceOfCommande(commande);
		} catch (BLLException e) {
		}
		assertEquals(23, prixCommande);
	}
	
	@Test
	@Sql("classpath:table_insertion.sql")
	void getTotalPriceOfCommande_tableInsertionCommande5_Return0() {
		Commande commande = service.getById(5);
		float prixCommande =0f;
		try {
			prixCommande = service.getTotalPriceOfCommande(commande);
		} catch (BLLException e) {
		}
		assertEquals(0, prixCommande);
	}
	
	@Test
	@Sql("classpath:table_insertion.sql")
	void getTotalPriceOfCommande_tableInsertionCommande6_ReturnException() {
		assertThrows(NoSuchElementException.class, () -> {
			service.getById(6);
	    });
	}
	
	@Test
	@Sql("classpath:table_insertion.sql")
	void getListeAddition_tableInsertionCommande1_ReturnListeTaille2() {
		Commande commande = service.getById(1);
		List<PlatCommandeWrapper> platsCommandes = new ArrayList<>();
		try {
			platsCommandes = service.getListeAddition(commande);
		} catch (BLLException e) {
		}
		assertEquals(2, platsCommandes.size());
		assertIterableEquals(
				Arrays.asList(new Integer[] {2, 1}),
				platsCommandes.stream().map(pc -> pc.getNbPlat()).collect(Collectors.toList())
		);
	}
	
	@Test
	@Sql("classpath:table_insertion.sql")
	void getListeAddition_tableInsertionCommande5_ReturnBLLException() {
		Commande commande = service.getById(5);
	    BLLException bllException = assertThrows(BLLException.class, () -> {
	        service.getListeAddition(commande);
	    });
	    assertEquals("La commande sélectionnée est vide", bllException.getMessage());
	}
}
