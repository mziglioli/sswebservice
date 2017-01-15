package com.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public abstract class DefaultSpecification<T> {

	public Specification<T> getById(Long id){
		return new Specification<T>() {
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				return cb.equal(root.get("id"), id);
			}
		};
	}
	
	protected boolean isCount(Class<? extends T> class1, CriteriaQuery<?> query){
		Class<?> clazz = query.getResultType();
		if (clazz.equals(class1.getClass())) {
			return false;
		}
		return true;
	}

	public abstract Specification<T> getBySearch(String search);
	
}