
package br.com.z2.contas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.text.WordUtils;

public class UsuarioDto {

	private Long id;

	private String nome;

	private String login;

	private Long perfil;

	private Boolean senhaPadrao;

	private String grupo;
	
	private List<String> grupos;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = WordUtils.capitalize(usuario.getNome());
		this.login = WordUtils.capitalize(usuario.getLogin());
		this.perfil = usuario.getPerfil();
		this.senhaPadrao = usuario.getSenhaPadrao();
		this.grupo = getNomeGrupos(usuario.getGrupos());
		this.setGrupos(getNomeGruposList(usuario.getGrupos()));
	}

	private String getNomeGrupos(List<Grupo> grupos) {
		return grupos == null ? "" :  grupos.stream().map(elem -> new String(elem.getNome())).collect(Collectors.joining(","));
	}
	
	private List<String> getNomeGruposList(List<Grupo> grupos) {
		return grupos == null ? null :  grupos.stream().map(elem -> new String(elem.getNome())).collect(Collectors.toList());
	}
	
	


	public Usuario getUsuario() {
		Usuario u = new Usuario();
		u.setId(getId());
		u.setNome(getNome());
		u.setPerfil(getPerfil());
		u.setSenhaPadrao(getSenhaPadrao());
		u.setLogin(getLogin().toLowerCase());
		return u;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getPerfil() {
		return perfil;
	}

	public void setPerfil(Long perfil) {
		this.perfil = perfil;
	}

	public Boolean getSenhaPadrao() {
		return senhaPadrao;
	}

	public void setSenhaPadrao(Boolean senhaPadrao) {
		this.senhaPadrao = senhaPadrao;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public List<String> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<String> grupos) {
		this.grupos = grupos;
	}
}
