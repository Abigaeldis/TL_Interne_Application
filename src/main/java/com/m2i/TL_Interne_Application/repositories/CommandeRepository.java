package com.m2i.TL_Interne_Application.repositories;

import org.springframework.data.repository.CrudRepository;

import com.m2i.TL_Interne_Application.entities.Commande;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {

}
