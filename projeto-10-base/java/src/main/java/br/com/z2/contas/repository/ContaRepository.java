
package br.com.z2.contas.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.z2.contas.model.Conta;
import org.springframework.data.domain.Pageable;

@Transactional
@Repository
public interface ContaRepository extends PagingAndSortingRepository<Conta, Long> {

	@Query("select t from Conta t where t.referencia.id = :referencia and ((:descricao = '') or lower(t.descricao) like lower(concat('%', :descricao,'%')) ) ")
	Page<Conta> listByPage(Pageable pageable, @Param("descricao") String descricao, @Param("referencia")Long referencia);

	@Modifying
	@Query(value = "insert into public.conta_padrao (id_conta) VALUES (:conta)", nativeQuery = true)
	void savePadrao(@Param("conta") Long  conta);
}


