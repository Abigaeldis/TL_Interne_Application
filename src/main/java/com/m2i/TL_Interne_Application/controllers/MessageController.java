package com.m2i.TL_Interne_Application.controllers;

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

import com.m2i.TL_Interne_Application.entities.Message;
import com.m2i.TL_Interne_Application.services.MessageService;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@GetMapping
	public Iterable<Message> getAll() {
		return messageService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Message> getById(@PathVariable int id) {
		Message message = messageService.getById(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Message> insert(@RequestBody Message message) {
		messageService.save(message);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Message message) {
		message.setId(id);
		messageService.save(message);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Message> delete(@PathVariable("id") int id) {
		Message message = messageService.getById(id);
		messageService.delete(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}