package com.m2i.TL_Interne_Application.controllers;

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

import com.m2i.TL_Interne_Application.entities.Plat;
import com.m2i.TL_Interne_Application.services.PlatService;

@RestController
@RequestMapping("/plats")
public class PlatController {

    @Autowired
    private PlatService platService;

    @GetMapping
    public ResponseEntity<Iterable<Plat>> getAllPlats() {
        Iterable<Plat> plats = platService.getAllPlats();
        return new ResponseEntity<>(plats, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plat> getPlatById(@PathVariable int id) {
        return platService.getPlatById(id)
                .map(plat -> new ResponseEntity<>(plat, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Plat> createPlat(@RequestBody Plat plat) {
        Plat createdPlat = platService.createPlat(plat);
        return new ResponseEntity<>(createdPlat, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plat> updatePlat(@PathVariable int id, @RequestBody Plat updatedPlat) {
        Plat updated = platService.updatePlat(id, updatedPlat);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlat(@PathVariable int id) {
        platService.deletePlat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
