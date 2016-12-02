package com.service;

import org.springframework.stereotype.Service;

import com.model.Category;
import com.repository.CategoryRepository;
import com.specification.CategorySpecification;

@Service
public class CategoryService extends ServiceDefault<Category, CategoryRepository> {

	public Category getCategoryWithArticles(Long id) {
		return getRepository().findOne(CategorySpecification.getCategoryWithArticles(id));
	}
}