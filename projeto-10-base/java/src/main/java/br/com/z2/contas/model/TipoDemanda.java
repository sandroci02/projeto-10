package br.com.z2.contas.model;

public enum TipoDemanda {

	ALERTA("Alerta"), MENSAGEM("Mensagem"), RETORNO("Retorno"), TAREFA("Tarefa");

	private String valor;

	private TipoDemanda(String valor) {
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
