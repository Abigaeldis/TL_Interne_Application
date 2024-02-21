package com.m2i.TL_Interne_Application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Plat;
import com.m2i.TL_Interne_Application.repositories.PlatRepository;

@Service
public class PlatService {

    @Autowired
    private PlatRepository platRepository;

    public Iterable<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Optional<Plat> getPlatById(int id) {
        return platRepository.findById(id);
    }

    public Plat createPlat(Plat plat) {
        return platRepository.save(plat);
    }

    public Plat updatePlat(int id, Plat updatedPlat) {
        if (platRepository.existsById(id)) {
            updatedPlat.setId(id);
            return platRepository.save(updatedPlat);
        } else {
            // Handle error, throw exception, etc.
            return null;
        }
    }

    public void deletePlat(int id) {
        platRepository.deleteById(id);
    }
}
