package br.com.z2.contas.model;

public enum StatusDemanda {
	ANDAMENTO("Em andamento"),AGUARDANDO("Aguardando"),CONCLUIDO("Concluído"),CANCELADO("Cancelado");
	
	private String valor;

	private StatusDemanda(String valor) {
		this.setValor(valor);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor;
	}
	
}
