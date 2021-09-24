package br.com.eventryapp.persistence;

import org.springframework.data.repository.CrudRepository;

import br.com.eventryapp.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{
	
	 Usuario findByEmail(String email);

}
