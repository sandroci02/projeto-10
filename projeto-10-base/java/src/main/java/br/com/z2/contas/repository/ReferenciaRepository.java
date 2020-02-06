package br.com.z2.contas.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.z2.contas.model.Referencia;
import org.springframework.data.domain.Pageable;

@Transactional
@Repository
public interface ReferenciaRepository extends PagingAndSortingRepository<Referencia, Long> {

	//@Query("select t from Referencia t LEFT JOIN FETCH t.contas LEFT JOIN FETCH t.rendimentos where ((:descricao = '') or lower(t.descricao) like lower(concat('%', :descricao,'%')) )")
	@Query("select t from Referencia t where ((:descricao = '') or lower(t.descricao) like lower(concat('%', :descricao,'%')) )")
	Page<Referencia> listByPage(Pageable pageable, @Param("descricao") String descricao);
}

