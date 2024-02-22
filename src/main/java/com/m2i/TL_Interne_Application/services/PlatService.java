package com.m2i.TL_Interne_Application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Carte;
import com.m2i.TL_Interne_Application.entities.Plat;
import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.repositories.PlatRepository;
import com.m2i.TL_Interne_Application.repositories.RestaurantRepository;

@Service
public class PlatService {

    @Autowired
    private PlatRepository platRepository;
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Iterable<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Optional<Plat> getPlatById(int id) {
        return platRepository.findById(id);
    }
    
    public List<Plat> getAllPlatsForRestaurant(int restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isEmpty()) {
            return null;
        }
        Restaurant restaurant = optionalRestaurant.get();
        
        Carte carte = restaurant.getCarte();
        if (carte == null) {
            return null;
        }
        return platRepository.findByCarte(carte);
    }
    
    public List<Plat> findByTypeLike(String type) {
        return platRepository.findByTypeLike(type);
    }

    public Plat createPlat(Plat plat) {
        return platRepository.save(plat);
    }

    public Plat updatePlat(int id, Plat updatedPlat) {
        if (platRepository.existsById(id)) {
            updatedPlat.setId(id);
            return platRepository.save(updatedPlat);
        } else {
            return null;
        }
    }

    public void deletePlat(int id) {
        platRepository.deleteById(id);
    }
}
