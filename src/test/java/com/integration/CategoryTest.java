package com.integration;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.conf.PersistenceConfigTest;
import com.conf.TestConfiguration;
import com.model.Category;
import com.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PersistenceConfigTest.class, TestConfiguration.class })
@Transactional
@Rollback
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:category_insert.sql")
public class CategoryTest {

	@Autowired
	private CategoryService categoryService;
	
	@Test
	public void testCategoryCount() {
		Long qtde = categoryService.getRepository().count();
		assertEquals(Long.valueOf(6), qtde);
	}

	@Test
	public void testPageable() {
		
		Comparator<Category> comparator = Comparator.comparing(Category::getId);
		int qtde = 5;
		int init = 0;
		int max = 0 + qtde;

		List<Category> list = reduce(init, max, comparator);
		assertEquals(5, list.size());
		
		init = max;
		max += qtde;

		list = reduce(init, max, comparator);
		assertEquals(1, list.size());		
	}
	
	private List<Category> reduce(int init, int max, Comparator<Category> comparator){
		Collection<Category> categories = categoryService.findAll();
		return categories.stream().sorted(comparator).skip(init).limit(max).collect(Collectors.toList());
	}
	
	@Test
	public void checkArticlesFromCategory() {
		Category category = categoryService.getCategoryWithArticles(1L);
		assertEquals(3, category.getArticles().size());
	}
}