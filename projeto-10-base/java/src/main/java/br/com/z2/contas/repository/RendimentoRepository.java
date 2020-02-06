
package br.com.z2.contas.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.z2.contas.model.Rendimento;
import org.springframework.data.domain.Pageable;

@Transactional
@Repository
public interface RendimentoRepository extends PagingAndSortingRepository<Rendimento, Long> {

	@Query("select t from Rendimento t where ((:descricao = '') or lower(t.descricao) like lower(concat('%', :descricao,'%')) )")
	Page<Rendimento> listByPage(Pageable pageable, @Param("descricao") String descricao);
}


