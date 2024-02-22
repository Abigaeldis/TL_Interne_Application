package com.m2i.TL_Interne_Application.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.TL_Interne_Application.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

	List<Reservation> findByDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);



}