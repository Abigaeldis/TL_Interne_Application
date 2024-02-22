package com.m2i.TL_Interne_Application.entities;

import lombok.Data;

@Data
public class NotificationWrapper {
	private Iterable<Message> messagesRecus;
	private Iterable<Reservation> reservationsEnAttente;
	
	public NotificationWrapper() {}
	
	public NotificationWrapper(Iterable<Message> messagesRecus, Iterable<Reservation> reservationsEnAttente) {
		this.messagesRecus = messagesRecus;
		this.reservationsEnAttente = reservationsEnAttente;
	}
}
