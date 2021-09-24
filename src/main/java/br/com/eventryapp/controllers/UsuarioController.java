package br.com.eventryapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eventryapp.model.Usuario;
import br.com.eventryapp.persistence.UsuarioRepository;

@Controller
@RequestMapping("/eventryapp/user")
public class UsuarioController {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	/**
	 * Esse metodo eh responsavel por retornar todos os usuarios registrados na base de dados eventrydb
	 * 
	 * @return usuarios: Lista de usuarios registrados na base de dados
	 * */
	@RequestMapping(value = "/getAllUsers", method=RequestMethod.GET)
	
	public @ResponseBody Iterable<Usuario> getAllUsuario() {	
       return usuarioRepository.findAll();
	}
	
	/**
	 * Esse metodo eh responsavel por cadastrar um usuario na base de dados eventrydb
	 * 
	 * @param: usuario: dados do usuario a ser cadastrado na base de dados
	 * @return usuario: retorna usuario cadastrado na base de dados
	 * 
	 * */
	@RequestMapping(value = "/createUser", method=RequestMethod.POST)
	public @ResponseBody Usuario createUsuario(@RequestBody Usuario usuario) {
		try {
			if(!usuarioJaCadastrado(usuario)) {
				return usuarioRepository.save(usuario);
			}else {
				return null;
			}
			  
			} catch (Exception ex) {
				return null;
			}
	}
	
	/**
	 * Esse metodo eh responsavel por efetuar o login de um usuario 
	 * 
	 * @param: usuario: dados do usuario que esta logando na aplicacao mobile
	 * @return usuario: login com sucesso: retorna dados do usuario logado
	 *                  login sem sucesso: retorna objeto nullo
	 * 
	 * */
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public ResponseEntity loggin(@RequestBody Usuario usuario) {
		Usuario userLogged = new Usuario();
		
		Usuario u = usuarioRepository.findByEmail(usuario.getEmail());
			 
		if(u!=null){
			if(u.getSenha().equals(usuario.getSenha())) {
				return ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body(u);	 
			}else {
				return ResponseEntity
						.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Senha incorreta. Tente Novamente");
			}
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Usuário não encontrado. Tente novamente!");
		}
	}
	
	/**
	 * Esse metodo eh responsavel por verificar se um usuario ja esta cadastrado no banco
	 * a verificacao eh feita pelo email, caso existir um email ja cadastrado no banco
	 * nao sera possivel cadastrar um usuario com o mesmo email
	 * 
	 * @param usuario: usuario a ser cadastrado no banco
	 * @return true: caso existir um email ja cadastro no banco
	 *         false: caso nao existir o email cadastrado n obanco
	 * */
	private boolean usuarioJaCadastrado(Usuario usuario) {
		Usuario u = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(u!=null)
			return true;
		return false;
	}
}
