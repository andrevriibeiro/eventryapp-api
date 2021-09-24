package br.com.eventryapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id; 
	@NotEmpty
	private String eventonome;
	private String descricao;
	@NotEmpty
	private String localizacao;
	private String codigoevento;
	@ManyToOne
	private Usuario usuario;
	
	private String dataevento;
	private String horaevento;
	
	@OneToMany
	private List<Convidado> convidados;
	
	@OneToMany
	private List<Presente> presentes = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventonome() {
		return eventonome;
	}

	public void setEventonome(String eventonome) {
		this.eventonome = eventonome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getCodigoevento() {
		return codigoevento;
	}

	public void setCodigoevento(String codigoevento) {
		this.codigoevento = codigoevento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Convidado> getConvidados() {
		return convidados;
	}

	public void setConvidados(List<Convidado> convidados) {
		this.convidados = convidados;
	}

	public List<Presente> getPresentes() {
		return presentes;
	}

	public void setPresentes(List<Presente> presentes) {
		this.presentes = presentes;
	}

	public String getDataevento() {
		return dataevento;
	}

	public void setDataevento(String dataevento) {
		this.dataevento = dataevento;
	}

	public String getHoraevento() {
		return horaevento;
	}

	public void setHoraevento(String horaevento) {
		this.horaevento = horaevento;
	}
}
