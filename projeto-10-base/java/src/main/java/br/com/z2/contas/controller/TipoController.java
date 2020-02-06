
package br.com.z2.contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Tipo;
import br.com.z2.contas.service.TipoService;
import br.com.z2.contas.sistema.PageData;
import br.com.z2.contas.sistema.ParametroData;
import br.com.z2.contas.sistema.helper.Helper;
import br.com.z2.contas.sistema.Paginador;
import org.springframework.data.domain.Page;


/**
* @autor: Alessandro Silva
* @email: sandro.ci02@gmail.com
* @date: 01/02/2019
*
*/

@RestController
@RequestMapping("/api/tipo")
public class TipoController {

	private static final String KEY = "tipo";
	
	private static final String INCLUIDA = "incluída";
	private static final String ALTERADA = "alterada";
	private static final String EXCLUIDA = "excluída";
	
	private static final String MSG_SUCESSO = "Tipo %s com sucesso";
	private static final String MSG_ERRO = "Erro ao executar a operação. Erro[%s]";
	
	
	@Autowired
	private TipoService service;
	
	@CrossOrigin
	@PostMapping("/find")
	public PageData find(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {
			Tipo tipo = service.find(entrada.getId());			
			saida.setEntidade(tipo);
			saida.setErro(false);
		}catch (Exception e) {
			saida.setErro(true);
			saida.setMensagem(e.getMessage());
		}
		return saida;
	}
	
	@CrossOrigin
	@PostMapping("/save")
	public PageData save(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {	
			Tipo tipo = entrada.getParametro(KEY, Tipo.class);			
			service.save(tipo);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, INCLUIDA));
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
		
	}
	
	@CrossOrigin
	@PostMapping("/update")
	public PageData update(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {
			Tipo tipo = entrada.getParametro(KEY, Tipo.class);			
			service.update(tipo);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, ALTERADA));
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
	
	@CrossOrigin
	@PostMapping("/delete")
	public PageData delete(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {
			Tipo tipo = service.find(entrada.getId());		
			service.remove(tipo);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, EXCLUIDA));
			saida.setErro(false);
		}catch (Exception e) {
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
	
	@CrossOrigin
	@PostMapping("/listAll")
	public PageData listAll(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			List<Tipo> list = service.listAll();			
			saida.setLista(list);
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
	
	@CrossOrigin
	@PostMapping("/list")
	public PageData list(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Page<Tipo> list = service.list(entrada);			
			saida.setLista(list.getContent());
			saida.setPaginador(new Paginador(list, entrada));
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
}


