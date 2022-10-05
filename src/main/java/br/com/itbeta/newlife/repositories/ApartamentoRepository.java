package br.com.itbeta.newlife.repositories;

import br.com.itbeta.newlife.models.Apartamento;
import br.com.itbeta.newlife.repositories.projections.AptoDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {

    @Query(value = "SELECT * FROM apartamentos a WHERE a.numApto = :query", nativeQuery = true)
    Page<Apartamento> findAllQuery(Pageable pageable, String query);

    @Query(value = "select numApto from apartamentos", nativeQuery = true)
    public Page<AptoDetails> findAllApto(Pageable pageable);

    @Query(value = "select * from apartamentos", nativeQuery = true)
    public List<AptoDetails> findAllAptos();
}
