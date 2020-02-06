package br.com.z2.contas.sistema;

public class SelecaoDto {
	private Object valor;
	private boolean selecionado;
	private boolean desabilitado;
	
	public SelecaoDto(Object valor, boolean selecionado) {
		super();
		this.valor = valor;
		this.selecionado = selecionado;
	}
	
	public SelecaoDto(Object valor) {
		super();
		this.valor = valor;
		this.selecionado = false;
		this.desabilitado = false;
	}
	
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	public boolean isSelecionado() {
		return selecionado;
	}
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public boolean isDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(boolean desabilitado) {
		this.desabilitado = desabilitado;
	}
	
	

}
