package com.m2i.TL_Interne_Application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Message;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.entities.Utilisateur;
import com.m2i.TL_Interne_Application.repositories.MessageRepository;
import com.m2i.TL_Interne_Application.repositories.RestaurantRepository;
import com.m2i.TL_Interne_Application.repositories.UtilisateurRepository;

@Service
public class MessageService {
	private MessageRepository messageRepo;
	private RestaurantRepository restaurantRepository;
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired 
	public MessageService(MessageRepository messageRepo,RestaurantRepository restaurantRepository,UtilisateurRepository utilisateurRepository) {
		this.messageRepo = messageRepo;
		this.restaurantRepository = restaurantRepository;
		this.utilisateurRepository = utilisateurRepository;
	}
	
	public Iterable<Message> getAll() { 
		return messageRepo.findAll(); 
		}
	
	public Message getById(int id) { 
		return messageRepo.findById(id).get(); 
		}
	
	public void save(Message message) throws BLLException {
		BLLException blleException = new BLLException();		
		
		if (message.getTitre().length() < 2) {
			blleException.ajouterErreur("Le titre du message doit faire au moins 2 caractères");
		}
		
		if (message.getTitre().length() > 50) {
			blleException.ajouterErreur("Le titre du message doit faire au maximum 50 caractères");
		}
		
		if (message.getCorpsMessage().length() < 2) {
			blleException.ajouterErreur("Le corps de votre message doit faire au moins 2 caractères");
		}
		
		if (message.getCorpsMessage().length() > 300) {
			blleException.ajouterErreur("Le corps de votre message doit faire au maximum 300 caractères");
		}
		
		if (message.getRestaurant() == null) {
			blleException.ajouterErreur("Veuillez affecter ce message à un restaurant.");
		}
		
		if (message.getUtilisateur() == null) {
			blleException.ajouterErreur("Veuillez affecter ce message à un utilisateur.");
		}
		
		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
	
		Restaurant restaurant = restaurantRepository.findById(message.getRestaurant().getId())
                .orElseThrow(() -> new RuntimeException("Restaurant non trouvé"));
	    // Associez le restaurant au message
		message.setRestaurant(restaurant);
		Utilisateur utilisateur = utilisateurRepository.findById(message.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
	    // Associez l'utilisateur à l'horaire
		message.setUtilisateur(utilisateur);
	    
		messageRepo.save(message); 
		}
	
	public void delete(int id) { 
		messageRepo.deleteById(id); 
		}
	
	public List<Message> findByRestaurant(Restaurant restaurant){
		return messageRepo.findByRestaurant(restaurant);
	}
}