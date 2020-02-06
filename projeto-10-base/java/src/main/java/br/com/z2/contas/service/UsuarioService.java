
package br.com.z2.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.z2.contas.exception.LoginDuplicadoException;
import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Usuario;
import br.com.z2.contas.model.UsuarioDto;
import br.com.z2.contas.sistema.ParametroData;

public interface UsuarioService {

	Usuario find(Long id) throws RegistroNaoEncontradoException;
	
	Usuario findByLogin(String login) throws RegistroNaoEncontradoException;

	void save(UsuarioDto usuario) throws LoginDuplicadoException;

	void update(UsuarioDto usuario);

	void remove(Usuario usuario);

	List<Usuario> listAll();

	Page<Usuario> list(ParametroData entrada);

	Usuario trocarSenha(String name, String senha);

	void resetSenha(Long id) throws RegistroNaoEncontradoException;
}


