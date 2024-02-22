package com.m2i.TL_Interne_Application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.entities.Table;
import com.m2i.TL_Interne_Application.repositories.TableRepository;

@Service
public class TableService {
	@Autowired
	private TableRepository tableRepository;
	
	public Iterable<Table> getAll(){
		return tableRepository.findAll();
	}
	
	public Table getById(int id) {
		return tableRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Table table) {
		tableRepository.save(table);
	}
	
	public void delete(Table table) {
		tableRepository.delete(table);
	}
	
	public void deleteById(int id) {
		tableRepository.deleteById(id);
	}
	
	public List<Table> findByRestaurant(Restaurant restaurant){
		return tableRepository.findByRestaurant(restaurant);
	}
}
