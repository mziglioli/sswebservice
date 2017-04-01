package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.model.Category;
import com.repository.CategoryRepository;
import com.specification.CategorySpecification;

@Service
public class CategoryService extends ServiceDefault<Category, CategoryRepository> {

	@Autowired
	private CategorySpecification specification;
	

	public Category getCategoryWithArticles(Long id) {
		return getRepository().findOne(specification.getById(id));
	}
	
	@Override
	public Page<Category> find(String search, Pageable pageable) {
		Specification<Category> spec = specification.getBySearch(search);
		return getRepository().findAll(spec, pageable);
	}
}