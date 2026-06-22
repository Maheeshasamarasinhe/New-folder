package com.example.user_management_system.specification;

import com.example.user_management_system.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Userspecification implements Specification<User> {

    private Map<String, String> searchCriteria;

    public Userspecification(Map<String, String> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria != null) {
            if (searchCriteria.containsKey("username") && searchCriteria.get("username") != null) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("username")),
                    "%" + searchCriteria.get("username").toLowerCase() + "%"
                ));
            }

            if (searchCriteria.containsKey("email") && searchCriteria.get("email") != null) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("email")),
                    "%" + searchCriteria.get("email").toLowerCase() + "%"
                ));
            }

            if (searchCriteria.containsKey("role") && searchCriteria.get("role") != null) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("role")),
                    "%" + searchCriteria.get("role").toLowerCase() + "%"
                ));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
