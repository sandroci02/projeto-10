package br.com.z2.contas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Referencia;
import br.com.z2.contas.repository.ReferenciaRepository;
import br.com.z2.contas.sistema.ParametroData;

@Service
public class ReferenciaServiceImpl implements ReferenciaService {

	private static final String KEY = "descricao";
	private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar a referência";
	@Autowired
	private ReferenciaRepository repositorio;
	
	@Override
	public Referencia find(Long id) throws RegistroNaoEncontradoException {
	 Optional<Referencia> op = repositorio.findById(id);
	 if(op.isPresent()) {
		 return op.get();
	 }
	 throw new RegistroNaoEncontradoException(MSG_ERRO_BUSCA);
	}

	@Override
	public void save(Referencia referencia) {
		repositorio.save(referencia);
	}

	@Override
	public void update(Referencia referencia) {
		repositorio.save(referencia);
	}

	@Override
	public void remove(Referencia referencia) {
		Optional<Referencia> objDelete = repositorio.findById(referencia.getId());	
		repositorio.delete(objDelete.get());
	}

	@Override
	public List<Referencia> listAll() {
		return (List<Referencia>) repositorio.findAll();
	}

	@Override
	public Page<Referencia> list(ParametroData entrada) {
		return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY));
	}
}
