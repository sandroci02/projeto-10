package br.com.z2.contas.sistema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class PageData {
	
	private Boolean erro;
	private String mensagem;
	private List lista;
	private Paginador paginador;
	private String identificador;
	private Object entidade;
	private Map<String,Object> auxiliar;
	
	public Boolean getErro() {
		return erro;
	}
	public void setErro(Boolean erro) {
		this.erro = erro;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List getLista() {
		return lista;
	}
	public void setLista(List lista) {
		this.lista = lista;
	}
	public Paginador getPaginador() {
		return paginador;
	}
	public void setPaginador(Paginador paginador) {
		this.paginador = paginador;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Object getEntidade() {
		return entidade;
	}
	public void setEntidade(Object entidade) {
		this.entidade = entidade;
	}
	public void addAuxiliar(String chave, Object valor) {
		getAuxiliar().put(chave, valor);
		
	}
	public Map<String,Object> getAuxiliar() {
		if(auxiliar == null) {
			auxiliar = new HashMap<>();
		}
		return auxiliar;
	}
	public void setAuxiliar(Map<String,Object> auxiliar) {
		this.auxiliar = auxiliar;
	}
}
