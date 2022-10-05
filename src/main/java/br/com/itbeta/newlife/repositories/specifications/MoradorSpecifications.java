package br.com.itbeta.newlife.repositories.specifications;

import br.com.itbeta.newlife.models.Morador;
import br.com.itbeta.newlife.models.Morador_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MoradorSpecifications {

    public static Specification<Morador> nomeLike(String info) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Morador_.nome), "%" + info + "%");
    }

    public static Specification<Morador> likeGenericQuery(String queryString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(2);
            predicates.add(nomeLike(queryString).toPredicate(root, query, criteriaBuilder));
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

}
