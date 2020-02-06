
package br.com.z2.contas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
	@SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Version
	private Long version;

	@Column
	private String nome;
	@Column
	private String senha;
	@Column
	private String login;
	@Column
	private Long perfil;

	@Column
	private Boolean senhaPadrao;

	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "grupos_usuarios", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = {
			@JoinColumn(name = "id_grupo") })
	private List<Grupo> grupos;
	
	public Usuario() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

	public void setPerfil(Long perfil) {
		this.perfil = perfil;
	}

	public Long getPerfil() {
		return this.perfil;
	}

	public Boolean getSenhaPadrao() {
		return senhaPadrao;
	}

	public void setSenhaPadrao(Boolean senhaPadrao) {
		this.senhaPadrao = senhaPadrao;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<Grupo> getGrupos() {
		if(grupos == null) {
			grupos = new ArrayList<Grupo>();
		}
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public void addGrupo(Grupo grupo) {
		getGrupos().add(grupo);
		
	}

	public void removeGrupo(Grupo grupo) {
		getGrupos().remove(grupo);
	}
}
