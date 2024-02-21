package com.m2i.TL_Interne_Application.repositories;

import org.springframework.data.repository.CrudRepository;

import com.m2i.TL_Interne_Application.entities.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
