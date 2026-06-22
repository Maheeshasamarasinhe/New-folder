package com.example.user_management_system.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class commonspecification<T> implements Specification<T> {

    private Map<String, String> searchCriteria;

    public commonspecification(Map<String, String> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria != null) {
            for (Map.Entry<String, String> entry : searchCriteria.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                if (value != null && !value.isEmpty()) {
                    predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(key).as(String.class)),
                        "%" + value.toLowerCase() + "%"
                    ));
                }
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
