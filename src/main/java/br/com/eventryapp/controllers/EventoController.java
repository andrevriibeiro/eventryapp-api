package br.com.eventryapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eventryapp.model.Convidado;
import br.com.eventryapp.model.Evento;
import br.com.eventryapp.model.EventoPresente;
import br.com.eventryapp.model.Presente;
import br.com.eventryapp.persistence.EventoRepository;
import br.com.eventryapp.persistence.PresenteRepository;

@Controller
@RequestMapping("/eventryapp/evento")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private PresenteRepository presenteRepository;
	
	/**
	 * Esse metodo eh responsavel por retornar todos os eventos registrados na base de dados eventrydb
	 * 
	 * @return eventos: Lista de eventos registrados na base de dados
	 * */
	@RequestMapping(value = "/getAllEventos", method=RequestMethod.GET)
	
	public @ResponseBody Iterable<Evento> getAllEventos() {	

		Iterable<Evento> e = eventoRepository.findAll();
		return e;
	}
	
	/**
	 * Esse metodo eh responsavel por cadastrar um evento na base de dados eventrydb
	 * 
	 * @param: evento: dados do evento a ser cadastrado na base de dados
	 * @return evento: retorna evento cadastrado na base de dados
	 * 
	 * */
	@RequestMapping(value = "/createEvento", method=RequestMethod.POST)
	public @ResponseBody Evento createEvento(@RequestBody Evento evento) {
		
		try {
			Evento e = eventoRepository.save(evento);
			return e;
			} catch (Exception ex) {
				return null;
			}
		
	}
	
	/**
	 * Esse metodo eh responsavel por atualizar um evento na base de dados eventrydb
	 * 
	 * @param: evento: dados do evento a ser atualizado na base de dados
	 * @return evento: retorna evento atualizado na base de dados
	 * 
	 * */
	@RequestMapping(value = "updateEvento", method=RequestMethod.POST)
	public @ResponseBody Evento updateEvento(@RequestBody Evento evento) {
		
		try {
			Evento e = eventoRepository.save(evento);
			return e;
			} catch (Exception ex) {
				return null;
			}
	}
	
	/**
	 * Esse metodo eh responsavel por deletar um evento na base de dados eventrydb
	 * 
	 * @param: evento Id: id do evento que deseja remover da base de dados
	 * @return ResponseEntity: Response Entity contendo status de sucesso ou não
	 * 
	 * */
	@RequestMapping(value = "/deletarEvento", method=RequestMethod.GET)
	public ResponseEntity deletarEvento(@RequestParam("id") int idEvento) {
		
		try {
			Evento evento = eventoRepository.findById(idEvento);
			eventoRepository.delete(evento);
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(null);	 
			} catch (Exception ex) {
				return ResponseEntity
						.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Problema no servidor");
			}
	}
	
	
	/**
	 * Esse metodo eh responsavel por deletar um evento na base de dados eventrydb
	 * 
	 * @param: evento Id: id do evento que deseja remover da base de dados
	 * @return ResponseEntity: Response Entity contendo status de sucesso ou não
	 * 
	 * */
	@RequestMapping(value = "/getEventoById", method=RequestMethod.GET)
	public @ResponseBody Evento getEventoById(@RequestParam("id") int idEvento) {
		
		try {
			return eventoRepository.findById(idEvento);	
			} catch (Exception ex) {
				return null;
			}
	}
	
	
	/**
	 * Esse metodo eh responsavel por deletar um presente de um evento na base de dados eventrydb
	 * 
	 * @param: eventoPresente: contendo a informação do presente que deseja remover
	 *         e do evento que deseja remover o presente.
	 *         
	 * @return ResponseEntity: Response Entity contendo status de sucesso ou não
	 * 
	 * */
	@RequestMapping(value = "/deletarEventoPresente", method=RequestMethod.POST)
	public ResponseEntity deletarEventoPresente(@RequestBody EventoPresente eventoPresente ) {
		
		try {
			Evento evento = eventoRepository.findById(eventoPresente.getEvento().getId());
			evento.getPresentes().removeIf(presente -> presente.getId().equals(eventoPresente.getPresente().getId()));
			eventoRepository.save(evento);
			presenteRepository.delete(eventoPresente.getPresente());
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(null);	 
			} catch (Exception ex) {
				return ResponseEntity
						.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Problema no servidor");
			}
	}
	
	/**
	 * Esse metodo eh responsavel por cadastrar um evento na base de dados eventrydb
	 * 
	 * @param: evento: dados do evento a ser cadastrado na base de dados
	 * @return evento: retorna evento cadastrado na base de dados
	 * 
	 * */
	@RequestMapping(value = "/createEventoPresente", method=RequestMethod.POST)
	public @ResponseBody Evento createEventoPresente(@RequestBody EventoPresente eventoPresente) {
		try {
			Presente p =  presenteRepository.save(eventoPresente.getPresente());
			Evento e = eventoRepository.findById(eventoPresente.getEvento().getId());
			e.getPresentes().add(p);
			return eventoRepository.save(e);
			} catch (Exception ex) {
				return null;
			}
	}
	
	/**
	 * Esse metodo eh responsavel por retornar todos os presentes de um evento
	 * 
	 * @param idEAVENTO: id do Evento que deseja que retorne a lista de presentes
	 * @return presente: lista de presentes do evento pesquisado
	 *
	 * */
	@RequestMapping(value = "/getAllPresentesByEvento", method=RequestMethod.GET)
	public @ResponseBody Iterable<Presente> getAllPresentesByEvento(@RequestParam("id") int idEvento) {
		
		try {
				Evento evento = eventoRepository.findById(idEvento);
				return evento.getPresentes();
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * Esse metodo eh responsavel por retornar todos os presentes de um evento
	 * 
	 * @param idEAVENTO: id do Evento que deseja que retorne a lista de presentes
	 * @return presente: lista de presentes do evento pesquisado
	 *
	 * */
	@RequestMapping(value = "/getAllConvidadosByEvento", method=RequestMethod.GET)
	public @ResponseBody Iterable<Convidado> getAllConvidadosByEvento(@RequestParam("id") int idEvento) {
		
		try {
				Evento evento = eventoRepository.findById(idEvento);
				return evento.getConvidados();
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * Esse metodo eh responsavel por retornar todos os eventos que um determinado Organiuzador 
	 * criou
	 * 
	 * @param idOrganiizador: id do Usuario organizador do evento
	 * @return eventos: lista dfe eventos cadastrado pelo organizador
	 *
	 * */
	@RequestMapping(value = "/findEventyByOrganizador", method=RequestMethod.GET)
	public @ResponseBody Iterable<Evento> findEventyByOrganizador(@RequestParam("id") int idOrganizador) {
		
		try {
				Iterable<Evento> eventos = eventoRepository.findByUsuarioId(idOrganizador);
				return eventos;
		} catch (Exception ex) {
			return null;
		}
	}
}
