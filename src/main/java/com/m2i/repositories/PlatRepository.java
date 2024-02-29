package com.m2i.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.entities.Carte;
import com.m2i.entities.Plat;

public interface PlatRepository extends CrudRepository<Plat, Integer> {
	List<Plat> findByCarte(Carte carte);
	List<Plat> findByTypeLike(String type);
}
