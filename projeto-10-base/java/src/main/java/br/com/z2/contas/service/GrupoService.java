
package br.com.z2.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Grupo;
import br.com.z2.contas.model.UsuarioDto;
import br.com.z2.contas.sistema.ParametroData;
import br.com.z2.contas.sistema.SelecaoDto;

public interface GrupoService {

	Grupo find(Long id) throws RegistroNaoEncontradoException;

	void save(Grupo grupo);

	void update(Grupo grupo);

	void remove(Grupo grupo);

	List<Grupo> listAll();

	Page<Grupo> list(ParametroData entrada);

	List<SelecaoDto> listUsuariosByGrupo(Grupo g);

	void setGrupo(UsuarioDto usuario, Grupo grupo, Boolean selecionado);

	List<SelecaoDto> listGrupos();

}


