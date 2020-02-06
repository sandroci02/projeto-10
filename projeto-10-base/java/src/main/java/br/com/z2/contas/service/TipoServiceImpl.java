
package br.com.z2.contas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Tipo;
import br.com.z2.contas.repository.TipoRepository;
import br.com.z2.contas.sistema.ParametroData;

@Service
public class TipoServiceImpl implements TipoService {

	private static final String KEY = "descricao";
	private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar o registro";
	@Autowired
	private TipoRepository repositorio;
	
	@Override
	public Tipo find(Long id) throws RegistroNaoEncontradoException {
	 Optional<Tipo> op = repositorio.findById(id);
	 if(op.isPresent()) {
		 return op.get();
	 }
	 throw new RegistroNaoEncontradoException(MSG_ERRO_BUSCA);
	}

	@Override
	public void save(Tipo tipo) {
		repositorio.save(tipo);
	}

	@Override
	public void update(Tipo tipo) {
		repositorio.save(tipo);
	}

	@Override
	public void remove(Tipo tipo) {
		Optional<Tipo> objDelete = repositorio.findById(tipo.getId());	
		repositorio.delete(objDelete.get());
	}

	@Override
	public List<Tipo> listAll() {
		return (List<Tipo>) repositorio.findAll();
	}

	@Override
	public Page<Tipo> list(ParametroData entrada) {
		return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY));
	}
}


