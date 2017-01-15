package com.specification;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.model.Category;

@Component
public class CategorySpecification extends DefaultSpecification<Category> {

	@Override
	public Specification<Category> getById(Long id) {
		return new Specification<Category>() {
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				root.fetch("articles", JoinType.LEFT);
				return cb.equal(root.get("id"), id);
			}
		};
	}

	@Override
	public Specification<Category> getBySearch(String s) {
		final String search = "%"+s+"%";
		return new Specification<Category>() {
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<>();
				query.distinct(true);
				Class<?> clazz = query.getResultType();
				if (clazz.equals(root.getJavaType())) {
					root.fetch("articles", JoinType.LEFT);
				}else{
					root.join("articles", JoinType.LEFT);
				}
				
				Predicate predName = cb.like(root.get("name"),search);
				Predicate predIcon = cb.like(root.get("icon"), search);
				Predicate predOr;
				
				if(StringUtils.isNumeric(s)){
					Predicate predId = cb.equal(root.get("id"), s);
					predOr = cb.or(predName,predIcon,predId);
				}else{
					predOr = cb.or(predName,predIcon);
				}
				predicates.add(predOr);
				query.orderBy(cb.asc(root.get("id")));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
