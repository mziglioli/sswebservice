package com.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}