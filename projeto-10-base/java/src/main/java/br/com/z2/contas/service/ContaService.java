
package br.com.z2.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Conta;
import br.com.z2.contas.sistema.ParametroData;

public interface ContaService {

	Conta find(Long id) throws RegistroNaoEncontradoException;

	void save(Conta conta);

	void update(Conta conta);

	void remove(Conta conta);

	List<Conta> listAll();

	Page<Conta> list(ParametroData entrada);
}


