
package br.com.z2.contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Grupo;
import br.com.z2.contas.model.UsuarioDto;
import br.com.z2.contas.service.GrupoService;
import br.com.z2.contas.sistema.PageData;
import br.com.z2.contas.sistema.Paginador;
import br.com.z2.contas.sistema.ParametroData;
import br.com.z2.contas.sistema.SelecaoDto;
import br.com.z2.contas.sistema.helper.Helper;

/**
* @autor: Alessandro Silva
* @email: sandro.ci02@gmail.com
* @date: 19/03/2019
*
*/

@RestController
@RequestMapping("/api/grupo")
public class GrupoController {

	private static final String KEY = "grupo";
	
	private static final String INCLUIDA = "incluída";
	private static final String ALTERADA = "alterada";
	private static final String EXCLUIDA = "excluída";
	
	private static final String MSG_SUCESSO = "Grupo %s com sucesso";
	private static final String MSG_ERRO = "Erro ao executar a operação. Erro[%s]";
	
	
	@Autowired
	private GrupoService service;
	
	@PostMapping("/find")
	public PageData find(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {
			Grupo grupo = entrada.getParametro(KEY, Grupo.class);			
			List<SelecaoDto> usuarios =  service.listUsuariosByGrupo(grupo);
			saida.addAuxiliar("usuarios", usuarios);
			
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
			Grupo grupo = entrada.getParametro(KEY, Grupo.class);			
			service.save(grupo);
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
			Grupo grupo = entrada.getParametro(KEY, Grupo.class);			
			service.update(grupo);
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
			Grupo grupo = service.find(entrada.getId());		
			service.remove(grupo);
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
			List<Grupo> list = service.listAll();			
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
			Page<Grupo> list = service.list(entrada);			
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
	
	@PostMapping("/selecionar")
	public PageData selecionar(@RequestBody ParametroData entrada){
		PageData saida = new PageData();
		try {
			Grupo grupo = entrada.getParametro(KEY, Grupo.class);			
			UsuarioDto usuario = entrada.getParametro("usuario", UsuarioDto.class);
			Boolean selecionado = entrada.getParametroBoolean("selecionado");
			
			service.setGrupo(usuario,grupo,selecionado);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, ALTERADA));
			saida.setErro(false);
		}catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO , e.getMessage()));
		}
		return saida;
	}
}


