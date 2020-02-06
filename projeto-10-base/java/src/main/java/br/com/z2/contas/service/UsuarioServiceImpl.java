
package br.com.z2.contas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.z2.contas.exception.LoginDuplicadoException;
import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Usuario;
import br.com.z2.contas.model.UsuarioDto;
import br.com.z2.contas.repository.UsuarioRepository;
import br.com.z2.contas.sistema.ParametroData;
import br.com.z2.contas.sistema.helper.UsuarioHelper;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final String MSG_O_LOGIN_JA_EXISTE = "O login já existe";
	private static final String KEY = "nome";
	private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar o registro";
	@Autowired
	private UsuarioRepository repositorio;
	
	@Override
	public Usuario find(Long id) throws RegistroNaoEncontradoException {
	 Optional<Usuario> op = repositorio.findById(id);
	 if(op.isPresent()) {
		 return op.get();
	 }
	 throw new RegistroNaoEncontradoException(MSG_ERRO_BUSCA);
	}

	@Override
	public void save(UsuarioDto usuario) throws LoginDuplicadoException {
		Usuario us = repositorio.findOneByLogin(usuario.getLogin());
		
		if(us != null) {
			throw new LoginDuplicadoException(MSG_O_LOGIN_JA_EXISTE);
		}
		Usuario save = usuario.getUsuario(); 
		
		UsuarioHelper.encriptaSenha(save);
		repositorio.save(save);
	}
	
	@Override
	public Usuario trocarSenha(String login, String senha) {
		Usuario usuario = repositorio.findOneByLogin(login);
		usuario.setSenha(senha);
		usuario.setSenhaPadrao(false);		
		UsuarioHelper.encriptaSenha(usuario);
		return repositorio.save(usuario);
	}

	@Override
	public void update(UsuarioDto usuario) {
		Optional<Usuario> obj = repositorio.findById(usuario.getId());
		Usuario save = usuario.getUsuario();
		if(obj.isPresent()) {
			save = UsuarioHelper.popula(obj.get(), save);
		}
		
		repositorio.save(save);
	}

	@Override
	public void remove(Usuario usuario) {		
		repositorio.delete(usuario);
	}

	@Override
	public List<Usuario> listAll() {
		return (List<Usuario>) repositorio.findAll();
	}

	@Override
	public Page<Usuario> list(ParametroData entrada) {
		return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY));
	}

	@Override
	public Usuario findByLogin(String login) throws RegistroNaoEncontradoException {
		return repositorio.findOneByLogin(login);
	}

	@Override
	public void resetSenha(Long id) throws RegistroNaoEncontradoException {
		Usuario usuario = find(id);
		usuario.setSenha(null);
		UsuarioHelper.encriptaSenha(usuario);
		repositorio.save(usuario);
	}
	
	
}


