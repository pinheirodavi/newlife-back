package br.com.itbeta.newlife.repositories;

import br.com.itbeta.newlife.models.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VisitanteRepository extends JpaRepository<Visitante, Long>, JpaSpecificationExecutor<Visitante> {
}
