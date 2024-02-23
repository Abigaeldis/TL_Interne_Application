package com.m2i.TL_Interne_Application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.TL_Interne_Application.entities.AdditionWrapper;
import com.m2i.TL_Interne_Application.entities.Commande;
import com.m2i.TL_Interne_Application.entities.PlatCommandeWrapper;
import com.m2i.TL_Interne_Application.services.BLLException;
import com.m2i.TL_Interne_Application.services.CommandeService;
import com.m2i.TL_Interne_Application.services.RestaurantService;

@RestController
@CrossOrigin
@RequestMapping("/commandes")
public class CommandeController {
	@Autowired
	private CommandeService commandeService;
	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public Iterable<Commande> getAll() {
		return commandeService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Commande> getById(@PathVariable int id) {
		Commande commande = commandeService.getById(id);
		if (commande != null) {
			return new ResponseEntity<>(commande, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<Commande>> getByRestaurant(@PathVariable int id) {
		List<Commande> commandes = commandeService.getByRestaurant(restaurantService.getById(id));
    	if (commandes != null) {
			return new ResponseEntity<>(commandes, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	 @GetMapping("/by-plat-commande-is-not-empty")
	    public ResponseEntity<List<Commande>> getByPlatCommandeIsNotEmpty() {
        List<Commande> commandes = commandeService.findByPlatCommandeIsNotEmpty();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }

    @GetMapping("/addition/{id}")
    public ResponseEntity<?> getAddition(@PathVariable("id") int commandeId) {
        Commande commande = commandeService.getById(commandeId);
        List<PlatCommandeWrapper> listePlats;
        float sommeTotale = (float) 0.0;
		try {
			listePlats = commandeService.getListeAddition(commande);
			sommeTotale = commandeService.getTotalPriceOfCommande(commande);
			AdditionWrapper addition = new AdditionWrapper(listePlats, sommeTotale);
	        return new ResponseEntity<>(addition, HttpStatus.OK);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
    }
    
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Commande commande) {
		try {
			commandeService.save(commande);
			return new ResponseEntity<>(commande, HttpStatus.CREATED);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Commande commande) {
		commande.setId(id);
		try {
			commandeService.save(commande);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Commande> delete(@PathVariable("id") int id) {
		Commande commande = commandeService.getById(id);
		commandeService.delete(id);
		return new ResponseEntity<>(commande, HttpStatus.OK);
	}

}