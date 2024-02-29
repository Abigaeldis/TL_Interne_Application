package com.m2i.controllers;

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

import com.m2i.entities.Message;
import com.m2i.services.BLLException;
import com.m2i.services.MessageService;

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
	public ResponseEntity<?> insert(@RequestBody Message message) {
		try {
			messageService.save(message);
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Message message) {
		message.setId(id);
		try {
			messageService.save(message);
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} catch (BLLException e) {
			return new ResponseEntity<>(e.getErreurs(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Message> delete(@PathVariable("id") int id) {
		Message message = messageService.getById(id);
		messageService.delete(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}