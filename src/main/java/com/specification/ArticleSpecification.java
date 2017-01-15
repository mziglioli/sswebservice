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

import com.model.Article;

@Component
public class ArticleSpecification extends DefaultSpecification<Article> {

	@Override
	public Specification<Article> getById(Long id) {
		return new Specification<Article>() {
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				root.fetch("categories", JoinType.LEFT);
				return cb.equal(root.get("id"), id);
			}
		};
	}

	@Override
	public Specification<Article> getBySearch(String s) {
		final String search = "%"+s+"%";
		return new Specification<Article>() {
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<>();
				query.distinct(true);
				
				Class<?> clazz = query.getResultType();
				if (clazz.equals(root.getJavaType())) {
					root.fetch("categories", JoinType.LEFT);
				}else{
					root.join("categories", JoinType.LEFT);
				}
				
				Predicate predTitle = cb.like(root.get("title"),search);
				Predicate predAnswer = cb.like(root.get("answer"), search);
				Predicate predAction = cb.like(root.get("action"), search);
				Predicate predActionName = cb.like(root.get("actionName"), search);
				Predicate predOr;
				
				if(StringUtils.isNumeric(s)){
					Predicate predId = cb.equal(root.get("id"), s);
					predOr = cb.or(predTitle, predAnswer, predAction, predActionName, predId);
				}else{
					predOr = cb.or(predTitle, predAnswer, predAction, predActionName);
				}
				predicates.add(predOr);
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
