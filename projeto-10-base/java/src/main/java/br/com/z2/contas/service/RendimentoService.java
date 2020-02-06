
package br.com.z2.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Rendimento;
import br.com.z2.contas.sistema.ParametroData;

public interface RendimentoService {

	Rendimento find(Long id) throws RegistroNaoEncontradoException;

	void save(Rendimento rendimento);

	void update(Rendimento rendimento);

	void remove(Rendimento rendimento);

	List<Rendimento> listAll();

	Page<Rendimento> list(ParametroData entrada);
}


