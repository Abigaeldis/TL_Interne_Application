package com.m2i.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.entities.PlatCommande;
import com.m2i.services.PlatCommandeService;

@RestController
@RequestMapping("/plat-commandes")
public class PlatCommandeController {
    private PlatCommandeService platCommandeService;

    @Autowired
    public PlatCommandeController(PlatCommandeService platCommandeService) {
    	this.platCommandeService = platCommandeService;
    }
    
    @GetMapping
    public ResponseEntity<Iterable<PlatCommande>> getAllPlatCommandes() {
    	Iterable<PlatCommande> platCommandes = platCommandeService.getAllPlatCommandes();
        return new ResponseEntity<>(platCommandes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatCommande> getPlatCommandeById(@PathVariable int id) {
        return platCommandeService.getPlatCommandeById(id)
                .map(platCommande -> new ResponseEntity<>(platCommande, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PlatCommande> createPlatCommande(@RequestBody PlatCommande platCommande) {
        PlatCommande createdPlatCommande = platCommandeService.createPlatCommande(platCommande);
        return new ResponseEntity<>(createdPlatCommande, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatCommande> updatePlatCommande(@PathVariable int id, @RequestBody PlatCommande updatedPlatCommande) {
        PlatCommande updated = platCommandeService.updatePlatCommande(id, updatedPlatCommande);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatCommande(@PathVariable int id) {
        platCommandeService.deletePlatCommande(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
