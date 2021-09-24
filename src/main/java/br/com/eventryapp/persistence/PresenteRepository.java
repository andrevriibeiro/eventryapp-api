package br.com.eventryapp.persistence;

import org.springframework.data.repository.CrudRepository;

import br.com.eventryapp.model.Presente;

public interface PresenteRepository extends CrudRepository<Presente, String> {

}
