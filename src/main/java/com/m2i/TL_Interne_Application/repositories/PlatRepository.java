package com.m2i.TL_Interne_Application.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.TL_Interne_Application.entities.Carte;
import com.m2i.TL_Interne_Application.entities.Plat;

public interface PlatRepository extends CrudRepository<Plat, Integer> {
	List<Plat> findByCarte(Carte carte);
	List<Plat> findByTypeLike(String type);
}
