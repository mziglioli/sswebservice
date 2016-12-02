package com.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.model.Article;

public class ArticleSpecification {

	public static Specification<Article> getArticleWithCategories(Long id) {
		return new Specification<Article>() {
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				root.fetch("categories", JoinType.LEFT);
				return cb.equal(root.get("id"), id);
			}
		};
	}
}
