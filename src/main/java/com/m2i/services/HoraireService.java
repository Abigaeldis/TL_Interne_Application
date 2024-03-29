package com.m2i.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.entities.Horaire;
import com.m2i.entities.Restaurant;
import com.m2i.repositories.HoraireRepository;
import com.m2i.repositories.RestaurantRepository;

@Service
public class HoraireService {
	private HoraireRepository horaireRepository;
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	public HoraireService(HoraireRepository horaireRepository, RestaurantRepository restaurantRepository) {
		this.horaireRepository = horaireRepository;
		this.restaurantRepository = restaurantRepository;
	}
	
	public Iterable<Horaire> getAll() {
		return horaireRepository.findAll();
	}
	
	public Horaire getById(int id) {
		return horaireRepository.findById(id).get();
	}

	public void save(Horaire horaire) throws BLLException {
		validateHoraire(horaire.getJour(), horaire.getCreneau());
		Restaurant restaurant = restaurantRepository.findById(horaire.getRestaurant().getId())
	                .orElseThrow(() -> new RuntimeException("Restaurant non trouvé"));
        // Associez le restaurant à l'horaire
        horaire.setRestaurant(restaurant);
		horaireRepository.save(horaire);
	}

	public void delete(int id ) {
		horaireRepository.deleteById(id);
	}
	
	public List<Horaire> findByRestaurant(Restaurant restaurant) {
		return horaireRepository.findByRestaurant(restaurant);
	}
	
	private void validateHoraire(String jour, String creneau) throws BLLException {
		BLLException blleException = new BLLException();
		// Validation du jour en s'affranchissant du case sensitive
		if (jour == null || !Pattern.matches("^(?i)(LUNDI|MARDI|MERCREDI|JEUDI|VENDREDI|SAMEDI|DIMANCHE)$", jour)) {
			blleException.ajouterErreur("Le jour doit être un jour de la semaine (LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE)");
		}
		
		if (creneau==null||!Pattern.matches("^(?i)(MIDI|SOIR)$", creneau)) {
			blleException.ajouterErreur("Le creneau doit être MIDI ou SOIR");
		}
		
		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
	}
}
