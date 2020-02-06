
package br.com.z2.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Tipo;
import br.com.z2.contas.sistema.ParametroData;

public interface TipoService {

	Tipo find(Long id) throws RegistroNaoEncontradoException;

	void save(Tipo tipo);

	void update(Tipo tipo);

	void remove(Tipo tipo);

	List<Tipo> listAll();

	Page<Tipo> list(ParametroData entrada);
}


