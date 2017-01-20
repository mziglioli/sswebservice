package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.model.Article;
import com.repository.ArticleRepository;
import com.specification.ArticleSpecification;

@Service
public class ArticleService extends ServiceDefault<Article, ArticleRepository> {

	@Autowired
	private ArticleSpecification specification;
	
	public Article getArticleWithCategories(Long id) {
		return getRepository().findOne(specification.getById(id));
	}
	
	@Override
	public Page<Article> find(String search, Pageable pageable) {
		Specification<Article> spec = specification.getBySearch(search);
		return getRepository().findAll(spec, pageable);
	}
}