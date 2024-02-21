package com.m2i.TL_Interne_Application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Message;
import com.m2i.TL_Interne_Application.repositories.MessageRepository;

@Service
public class MessageService {
	@Autowired private MessageRepository messageRepo;
	
	public Iterable<Message> getAll() { 
		return messageRepo.findAll(); 
		}
	
	public Message getById(int id) { 
		return messageRepo.findById(id).get(); 
		}
	
	public void save(Message message) {
		messageRepo.save(message); 
		}
	
	public void delete(int id) { 
		messageRepo.deleteById(id); 
		}
}