package br.com.itbeta.newlife.repositories.specifications;

import br.com.itbeta.newlife.models.Veiculo;
import br.com.itbeta.newlife.models.Veiculo_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class VeiculoSpecifications {
    public static Specification<Veiculo> modeloLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Veiculo_.modelo), "%"+info+"%");
    }

    public static Specification<Veiculo> placaLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Veiculo_.placa), "%"+info+"%");
    }

    public static Specification<Veiculo> marcaLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Veiculo_.marca), "%"+info+"%");
    }

    public static Specification<Veiculo> likeGenericQuery(String queryString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(4);
            predicates.add(modeloLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(placaLike(queryString).toPredicate(root, query, criteriaBuilder));
            predicates.add(marcaLike(queryString).toPredicate(root, query, criteriaBuilder));
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
