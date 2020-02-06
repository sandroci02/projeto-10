

package br.com.z2.contas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;


@Entity
public class Tipo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_id_seq")
    @SequenceGenerator(name = "tipo_id_seq", sequenceName = "tipo_id_seq" , initialValue = 1, allocationSize = 1)    
	private Long id;
	
	@Version
	private Long version;  

	public Long getVersion() {
		return version;
	}


	public void setVersion(Long version) {
		this.version = version;
	}

	@Column 
	private String descricao;
	
	public Tipo() {}

	
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
		
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return this.descricao;
	}
				
}
	