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
import com.m2i.TL_Interne_Application.entities.Table;
import com.m2i.TL_Interne_Application.services.BLLException;
import com.m2i.TL_Interne_Application.services.CommandeService;
import com.m2i.TL_Interne_Application.services.RestaurantService;
import com.m2i.TL_Interne_Application.services.TableService;

@RestController
@CrossOrigin
@RequestMapping("/commandes")
public class CommandeController {
	@Autowired
	private CommandeService commandeService;
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private TableService tableService;

	@GetMapping
	public Iterable<Commande> getAll() {
		return commandeService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Commande> getById(@PathVariable int id) {
		Commande commande = commandeService.getById(id);
		if (commande != null) {
			return new ResponseEntity<>(commande, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<Commande>> getByRestaurant(@PathVariable int id) {
		List<Commande> commandes = commandeService.getByRestaurant(restaurantService.getById(id));
		if (commandes != null) {
			return new ResponseEntity<>(commandes, HttpStatus.OK);
		} else {
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

	@GetMapping("/restaurant/{restaurantId}/table/{tableNum}/statut/{statut}")
	public ResponseEntity<List<Commande>> getCommandsByTableByStatut(@PathVariable int restaurantId,
	        @PathVariable int tableNum, @PathVariable String statut) {


	    List<Table> tables = tableService.findByRestaurant(restaurantService.getById(restaurantId));

	    Table selectedTable = null;
	    for (Table table : tables) {
	        if (table.getNumTable() == tableNum) {
	            selectedTable = table;
	            break;
	        }
	    }
	    
	    List<Commande> commands = commandeService.getCommandsByTableAndStatut(selectedTable, statut);
	    
	    if (commands.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<>(commands, HttpStatus.OK);
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

	@PutMapping("/{id}/lancee")
	public ResponseEntity<?> updateStatutLancee(@PathVariable("id") int id) {
		Commande commande = commandeService.getById(id);
		commande.setId(id);
		try {
			commande.setStatut("Lancee");
			commandeService.save(commande);
			return new ResponseEntity<>(commande, HttpStatus.OK);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/{id}/prete")
	public ResponseEntity<?> updateStatutPrete(@PathVariable("id") int id) {
		Commande commande = commandeService.getById(id);
		commande.setId(id);
		try {
			commande.setStatut("Prete");
			commandeService.save(commande);
			return new ResponseEntity<>(commande, HttpStatus.OK);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/{id}/servie")
	public ResponseEntity<?> updateStatutServie(@PathVariable("id") int id) {
		Commande commande = commandeService.getById(id);
		commande.setId(id);
		try {
			commande.setStatut("Servie");
			commandeService.save(commande);
			return new ResponseEntity<>(commande, HttpStatus.OK);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/{id}/reglee")
	public ResponseEntity<?> updateStatutReglee(@PathVariable("id") int id) {
		Commande commande = commandeService.getById(id);
		commande.setId(id);
		try {
			commande.setStatut("Reglee");
			commandeService.save(commande);
			return new ResponseEntity<>(commande, HttpStatus.OK);
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