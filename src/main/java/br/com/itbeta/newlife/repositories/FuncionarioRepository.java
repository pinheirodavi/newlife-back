package br.com.itbeta.newlife.repositories;

import br.com.itbeta.newlife.models.Funcionario;
import br.com.itbeta.newlife.models.Funcionario_;
import br.com.itbeta.newlife.models.Visitante;
import br.com.itbeta.newlife.models.Visitante_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {

}
