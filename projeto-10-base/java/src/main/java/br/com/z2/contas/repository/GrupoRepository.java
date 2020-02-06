
package br.com.z2.contas.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.z2.contas.model.Grupo;
import org.springframework.data.domain.Pageable;

@Transactional
@Repository
public interface GrupoRepository extends PagingAndSortingRepository<Grupo, Long> {

	@Query("select t from Grupo t where ((:nome = '') or lower(t.nome) like lower(concat('%', :nome,'%')) )")
	Page<Grupo> listByPage(Pageable pageable, @Param("nome") String nome);

	List<Grupo> findAllByOrderByNomeAsc();
}


