
package br.com.z2.contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Conta;
import br.com.z2.contas.model.Referencia;
import br.com.z2.contas.service.ContaService;
import br.com.z2.contas.service.ReferenciaService;
import br.com.z2.contas.sistema.PageData;
import br.com.z2.contas.sistema.ParametroData;
import br.com.z2.contas.sistema.helper.Helper;

import br.com.z2.contas.sistema.Paginador;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

	private static final String KEY = "conta";
	
	private static final String INCLUIDA = "incluída";
	private static final String ALTERADA = "alterada";
	private static final String EXCLUIDA = "excluída";
	
	private static final String MSG_SUCESSO = "Conta %s com sucesso";
	private static final String MSG_ERRO = "Erro ao executar a operação. Erro[%s]";
	
	
	@Autowired
	private ContaService service;
	
	@Autowired
	private ReferenciaService referenciaService;
	
	@CrossOrigin
	@PostMapping("/find")
	public PageData find(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {
			Conta conta = service.find(entrada.getId());			
			saida.setEntidade(conta);
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
			Conta conta = entrada.getParametro(KEY, Conta.class);			
			service.save(conta);
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
			Conta conta = entrada.getParametro(KEY, Conta.class);			
			service.update(conta);
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
			Conta conta = service.find(entrada.getId());		
			service.remove(conta);
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
			List<Conta> list = service.listAll();
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
			Page<Conta> list = service.list(entrada);			
			saida.setLista(list.getContent());
			saida.setPaginador(new Paginador(list, entrada));
			saida.setErro(false);
			
			Referencia referencia = referenciaService.find(entrada.getFiltroLong("referencia"));
			saida.addAuxiliar("referencia", referencia);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
}


