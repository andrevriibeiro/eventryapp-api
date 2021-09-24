package br.com.eventryapp.persistence;

import org.springframework.data.repository.CrudRepository;
import br.com.eventryapp.model.Evento;
public interface EventoRepository extends CrudRepository<Evento, String> {

	Iterable<Evento> findByUsuarioId(int usuario);
	
	Evento findById(int id);
}
 