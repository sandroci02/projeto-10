package br.com.z2.contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Referencia;
import br.com.z2.contas.service.ReferenciaService;
import br.com.z2.contas.sistema.PageData;
import br.com.z2.contas.sistema.Paginador;
import br.com.z2.contas.sistema.ParametroData;
import br.com.z2.contas.sistema.helper.Helper;

@RestController
@RequestMapping("/api/referencia")
public class ReferenciaController {

	private static final String KEY = "referencia";
	
	private static final String INCLUIDA = "incluída";
	private static final String ALTERADA = "alterada";
	private static final String EXCLUIDA = "excluída";
	
	private static final String MSG_SUCESSO = "Referência %s com sucesso";
	private static final String MSG_ERRO = "Erro ao executar a operação. Erro[%s]";
	
	
	@Autowired
	private ReferenciaService service;
	
	@CrossOrigin
	@PostMapping("/find")
	public PageData find(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Referencia referencia = service.find(entrada.getId());			
			saida.setEntidade(referencia);
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(e.getMessage());
		}
		return saida;
	}
	
	@CrossOrigin
	@PostMapping("/save")
	public PageData save(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Referencia referencia = entrada.getParametro(KEY, Referencia.class);			
			service.save(referencia);
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
	public PageData update(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Referencia referencia = entrada.getParametro(KEY, Referencia.class);			
			service.update(referencia);
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
	public PageData delete(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Referencia referencia = service.find(entrada.getId());		
			service.remove(referencia);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, EXCLUIDA));
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
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
			List<Referencia> list = service.listAll();			
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
	public PageData list( Authentication authentication , @RequestHeader(value="Authorization") String token, @RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		
		System.out.println(token);
		System.out.println(authentication.getName());
		
		PageData saida = new PageData();
		try {
			Page<Referencia> list = service.list(entrada);			
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
