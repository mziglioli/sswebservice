package com.repository;

import com.model.Category;

public interface CategoryRepository extends DefaultRepository<Category> {

	public Category findByName(String name);
}