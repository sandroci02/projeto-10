
package br.com.z2.contas.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_id_seq")
	@SequenceGenerator(name = "conta_id_seq", sequenceName = "conta_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Version
	private Long version;

	@Column
	private String descricao;

	@Column
	private Boolean pago;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_tipo", nullable=false)
	private Tipo tipo;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_referencia", nullable=false)
	private Referencia referencia;
	
	@Column
	private BigDecimal valor;
	
	@Column
	private Boolean padrao;

	public Conta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Referencia getReferencia() {
		return referencia;
	}

	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getPadrao() {
		return padrao;
	}

	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}
}
