
package br.com.z2.contas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Conta;
import br.com.z2.contas.model.Referencia;
import br.com.z2.contas.model.Tipo;
import br.com.z2.contas.repository.ContaRepository;
import br.com.z2.contas.repository.ReferenciaRepository;
import br.com.z2.contas.repository.TipoRepository;
import br.com.z2.contas.sistema.ParametroData;

@Service
public class ContaServiceImpl implements ContaService {

	private static final String KEY = "descricao";
	private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar o registro";
	
	@Autowired
	private ReferenciaRepository referenciaRepository;
	
	@Autowired
	private TipoRepository tipoRepository;
	
	@Autowired
	private ContaRepository repositorio;
	
	@Override
	public Conta find(Long id) throws RegistroNaoEncontradoException {
	 Optional<Conta> op = repositorio.findById(id);
	 if(op.isPresent()) {
		 return op.get();
	 }
	 throw new RegistroNaoEncontradoException(MSG_ERRO_BUSCA);
	}

	@Override
	public void save(Conta conta) {
		preparaConta(conta);
	}

	@Override
	public void update(Conta conta) {
		preparaConta(conta);
	}

	private void preparaConta(Conta conta) {
		Referencia referenciaLoad = referenciaRepository.findById(conta.getReferencia().getId()).get();
		conta.setReferencia(referenciaLoad);
		
		Tipo tipoLoad = tipoRepository.findById(conta.getTipo().getId()).get();
		conta.setTipo(tipoLoad);
		conta = repositorio.save(conta);
		
		if(Boolean.TRUE.equals(conta.getPadrao())){
			repositorio.savePadrao(conta.getId());
		}
	}

	@Override
	public void remove(Conta conta) {
		Optional<Conta> objDelete = repositorio.findById(conta.getId());	
		repositorio.delete(objDelete.get());
	}

	@Override
	public List<Conta> listAll() {
		return (List<Conta>) repositorio.findAll();
	}

	@Override
	public Page<Conta> list(ParametroData entrada) {
		return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY), entrada.getFiltroLong("referencia"));
	}
}


