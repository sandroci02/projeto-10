
package br.com.z2.contas.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.z2.contas.model.Tipo;
import org.springframework.data.domain.Pageable;

@Transactional
@Repository
public interface TipoRepository extends PagingAndSortingRepository<Tipo, Long> {

	@Query("select t from Tipo t where ((:descricao = '') or lower(t.descricao) like lower(concat('%', :descricao,'%')) )")
	Page<Tipo> listByPage(Pageable pageable, @Param("descricao") String descricao);
}


