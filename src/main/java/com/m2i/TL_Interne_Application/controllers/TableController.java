package com.m2i.TL_Interne_Application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.TL_Interne_Application.entities.Table;
import com.m2i.TL_Interne_Application.services.TableService;

@RestController
@CrossOrigin
@RequestMapping("/tables")
public class TableController {
	@Autowired
	private TableService service;
	
	@GetMapping
	public Iterable<Table> getAll(){
		return service.getAll();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Table> getById(@PathVariable("id") int id){
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Table table){
		service.saveOrUpdate(table);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id){
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteById(@RequestBody Table table){
		service.delete(table);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
