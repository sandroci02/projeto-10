
package br.com.z2.contas.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.z2.contas.model.Usuario;
import org.springframework.data.domain.Pageable;

@Transactional
@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	@Query("select t from Usuario t where ((:nome = '') or lower(t.nome) like lower(concat('%', :nome,'%')) )")
	Page<Usuario> listByPage(Pageable pageable, @Param("nome") String nome);

	Usuario findOneByLogin(String username);
	
	List<Usuario> findAllByOrderByNomeAsc();
}


