package com.example.user_management_system.specification;

import com.example.user_management_system.entity.Group;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupSpecification implements Specification<Group> {

    private Map<String, String> searchCriteria;

    public GroupSpecification(Map<String, String> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria != null) {
            if (searchCriteria.containsKey("name") && searchCriteria.get("name") != null) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + searchCriteria.get("name").toLowerCase() + "%"
                ));
            }

            if (searchCriteria.containsKey("description") && searchCriteria.get("description") != null) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")),
                    "%" + searchCriteria.get("description").toLowerCase() + "%"
                ));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
