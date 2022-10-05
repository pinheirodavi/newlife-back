package br.com.itbeta.newlife.repositories;

import br.com.itbeta.newlife.models.Morador;
import br.com.itbeta.newlife.repositories.projections.MoradorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoradorRepository extends JpaRepository<Morador, Long>, JpaSpecificationExecutor<Morador> {

    @Query(value = "select apartamentos.numApto as IdApto, moradores.morador, moradores.rg, moradores.cpf, moradores.tel1, moradores.tel2, moradores.email, moradores.contatoEmergencia, moradores.telEmergencia, moradores.obs from apartamentos join moradores on apartamentos.idApto = moradores.idApto", nativeQuery = true)
    List<MoradorDetails> findAllList();

    @Query(value = "select apartamentos.numApto as IdApto, moradores.morador, moradores.rg, moradores.cpf, moradores.tel1, moradores.tel2, moradores.email, moradores.contatoEmergencia, moradores.telEmergencia, moradores.obs from apartamentos join moradores on apartamentos.idApto = moradores.idApto where moradores.morador like %:query%", nativeQuery = true)
    List<MoradorDetails> findAllList(String query);


}
