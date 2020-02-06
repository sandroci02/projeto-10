
package br.com.z2.contas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.z2.contas.exception.RegistroNaoEncontradoException;
import br.com.z2.contas.model.Grupo;
import br.com.z2.contas.model.Usuario;
import br.com.z2.contas.model.UsuarioDto;
import br.com.z2.contas.repository.GrupoRepository;
import br.com.z2.contas.repository.UsuarioRepository;
import br.com.z2.contas.sistema.ParametroData;
import br.com.z2.contas.sistema.SelecaoDto;
import br.com.z2.contas.sistema.helper.UsuarioHelper;

@Service
public class GrupoServiceImpl implements GrupoService {

	private static final String KEY = "nome";
	private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar o registro";

	@Autowired
	private GrupoRepository repositorio;

	@Autowired
	private UsuarioRepository repositorioUsuario;

	@Override
	public Grupo find(Long id) throws RegistroNaoEncontradoException {
		Optional<Grupo> op = repositorio.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		throw new RegistroNaoEncontradoException(MSG_ERRO_BUSCA);
	}

	@Override
	public void save(Grupo grupo) {
		repositorio.save(grupo);
	}

	@Override
	public void update(Grupo grupo) {
		Optional<Grupo> objUpdate = repositorio.findById(grupo.getId());
		Grupo save = objUpdate.get();
		save.setNome(grupo.getNome());
		save.setAtivo(grupo.getAtivo());

		repositorio.save(save);
	}

	@Override
	public void remove(Grupo grupo) {
		Optional<Grupo> objDelete = repositorio.findById(grupo.getId());
		repositorio.delete(objDelete.get());
	}

	@Override
	public List<Grupo> listAll() {
		return (List<Grupo>) repositorio.findAll();
	}

	@Override
	public Page<Grupo> list(ParametroData entrada) {
		return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY));
	}

	@Override
	public List<SelecaoDto> listUsuariosByGrupo(Grupo g) {
		List<Usuario> usuarios = repositorioUsuario.findAllByOrderByNomeAsc();
		List<SelecaoDto> retorno = new ArrayList<SelecaoDto>();
		for (Usuario usuario : usuarios) {
			SelecaoDto selecaoDto = new SelecaoDto(UsuarioHelper.apresentacao(usuario));
			List<Grupo> grupos = usuario.getGrupos();
			if (g != null && grupos != null) {
				for (Grupo grupo : grupos) {
					if (g.getId() != null && g.getId().equals(grupo.getId())) {
						selecaoDto.setSelecionado(true);
					}
				}

			}
			retorno.add(selecaoDto);
		}
		return retorno;

	}
	
	@Override
	public void setGrupo(UsuarioDto usuario, Grupo grupo, Boolean selecionado) {
		Optional<Usuario> usuarioBanco = repositorioUsuario.findById(usuario.getId());
		Optional<Grupo> grupoBanco = repositorio.findById(grupo.getId());

		if (usuarioBanco.isPresent() && grupoBanco.isPresent()) {
			Usuario save = usuarioBanco.get();
			grupo = grupoBanco.get();

			System.out.println("usuario" + save.getId());
			System.out.println("grupo" + grupo.getId());

			if (selecionado) {
				save.addGrupo(grupo);
			} else {
				save.removeGrupo(grupo);
			}
			repositorioUsuario.save(save);
		}

	}

	@Override
	public List<SelecaoDto> listGrupos() {
		List<Grupo> grupos = repositorio.findAllByOrderByNomeAsc();
		List<SelecaoDto> retorno = new ArrayList<SelecaoDto>();
		for (Grupo grupo : grupos) {
			SelecaoDto selecaoDto = new SelecaoDto(grupo);
			retorno.add(selecaoDto);
		}
		return retorno;

	}
	
}
