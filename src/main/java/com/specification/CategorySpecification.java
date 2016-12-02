package com.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.model.Category;

public class CategorySpecification {

	public static Specification<Category> getCategoryWithArticles(Long id) {
		return new Specification<Category>() {
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				root.fetch("articles", JoinType.LEFT);
				return cb.equal(root.get("id"), id);
			}
		};
	}
}
