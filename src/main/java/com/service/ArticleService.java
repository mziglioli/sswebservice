package com.service;

import org.springframework.stereotype.Service;

import com.model.Article;
import com.repository.ArticleRepository;
import com.specification.ArticleSpecification;

@Service
public class ArticleService extends ServiceDefault<Article, ArticleRepository> {

	public Article getArticleWithCategories(Long id) {
		return getRepository().findOne(ArticleSpecification.getArticleWithCategories(id));
	}
}