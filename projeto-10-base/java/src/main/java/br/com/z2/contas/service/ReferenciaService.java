package br.com.z2.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Referencia;
import br.com.z2.contas.sistema.ParametroData;

public interface ReferenciaService {

	Referencia find(Long id) throws RegistroNaoEncontradoException;

	void save(Referencia referencia);

	void update(Referencia referencia);

	void remove(Referencia referencia);

	List<Referencia> listAll();

	Page<Referencia> list(ParametroData entrada);
}
