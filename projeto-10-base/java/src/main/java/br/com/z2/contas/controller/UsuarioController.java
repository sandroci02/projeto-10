
package br.com.z2.contas.controller;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Usuario;
import br.com.z2.contas.model.UsuarioDto;
import br.com.z2.contas.service.UsuarioService;
import br.com.z2.contas.sistema.PageData;
import br.com.z2.contas.sistema.ParametroData;
import br.com.z2.contas.sistema.helper.Helper;
import br.com.z2.contas.sistema.helper.UsuarioHelper;
import br.com.z2.contas.sistema.Paginador;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

/**
* @autor: Alessandro Silva
* @email: sandro.ci02@gmail.com
* @date: 11/03/2019
*
*/

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private static final String KEY = "usuario";
	
	private static final String INCLUIDA = "incluída";
	private static final String ALTERADA = "alterada";
	private static final String EXCLUIDA = "excluída";
	
	private static final String MSG_SUCESSO = "Usuario %s com sucesso";
	private static final String MSG_ERRO = "Erro ao executar a operação. Erro[%s]";
	
	
	@Autowired
	private UsuarioService service;
	
	@CrossOrigin
	@PostMapping("/info")
	public PageData get(Authentication authentication , @RequestHeader(value="Authorization") String token, @RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Usuario usuario = service.findByLogin(authentication.getName());
			saida.setEntidade(UsuarioHelper.apresentacao(usuario));
			saida.addAuxiliar("padrao",usuario.getSenhaPadrao());
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
	

	@CrossOrigin
	@PostMapping("/reset")
	public PageData reset(Authentication authentication , @RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {										
			service.resetSenha(entrada.getId());
			saida.setMensagem("Reset da senha efetuado com sucesso");
			saida.setErro(false);
		}catch (Exception e) {
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
	
	
	@CrossOrigin
	@PostMapping("/senha")
	public PageData senha(Authentication authentication , @RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			String senha = entrada.getParametro("senha");					
			Usuario usuario = service.trocarSenha(authentication.getName(),senha);
			saida.setEntidade(UsuarioHelper.apresentacao(usuario));
			saida.addAuxiliar("padrao",usuario.getSenhaPadrao());
			saida.setErro(false);
		}catch (Exception e) {
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
	
	@PostMapping("/find")
	public PageData find(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {
			Usuario usuario = service.find(entrada.getId());			
			saida.setEntidade(usuario);
			saida.setErro(false);
		}catch (Exception e) {
			saida.setErro(true);
			saida.setMensagem(e.getMessage());
		}
		return saida;
	}
	
	@PostMapping("/save")
	public PageData save(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {	
			UsuarioDto usuario = entrada.getParametro(KEY, UsuarioDto.class);			
			service.save(usuario);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, INCLUIDA));
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
		
	}
	
	@PostMapping("/update")
	public PageData update(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {
			UsuarioDto usuario = entrada.getParametro(KEY, UsuarioDto.class);			
			service.update(usuario);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, ALTERADA));
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
	
	@PostMapping("/delete")
	public PageData delete(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {
			Usuario usuario = service.find(entrada.getId());		
			service.remove(usuario);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, EXCLUIDA));
			saida.setErro(false);
		}catch (Exception e) {
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
	
	
	@PostMapping("/listAll")
	public PageData listAll(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			List<Usuario> list = service.listAll();			
			saida.setLista(list);
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
	
	@PostMapping("/list")
	public PageData list(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Page<Usuario> list = service.list(entrada);			
			saida.setLista(UsuarioHelper.apresentacao(list.getContent()));
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


