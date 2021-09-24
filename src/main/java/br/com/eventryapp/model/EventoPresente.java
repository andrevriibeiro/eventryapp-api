package br.com.eventryapp.model;

import java.io.Serializable;

public class EventoPresente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Evento evento;
	private Presente presente;
	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Presente getPresente() {
		return presente;
	}
	public void setPresente(Presente presente) {
		this.presente = presente;
	}
	
	

}
